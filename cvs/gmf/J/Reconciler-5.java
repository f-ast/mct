/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michael Golubev (Borland) - initial API and implementation
 */

package org.eclipse.gmf.internal.common.reconcile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class Reconciler {
	/**
	 * Expected max breadth of the node in the reconciled tree
	 */
	private static final int PAIRS_POOL_SIZE = 100;
	
	/**
	 * Expected depth of the reconciled tree 
	 */
	private static final int STORAGE_POOL_SIZE = 10;
	
	private final ReconcilerConfig myConfig;
	private final MatchingSession myMatchingSession;
	private final StoragePool myStoragePool;

	public Reconciler(ReconcilerConfig config){
		myConfig = config;
		myMatchingSession = new MatchingSession(new PairsPool(PAIRS_POOL_SIZE));
		myStoragePool = new StoragePool(STORAGE_POOL_SIZE);
	}
	
	protected void handleNotMatchedCurrent(EObject current){
		//FIXME ??? Is it user escape -- check history ???
		//FIXME How to handle not macthed old ???
	}
	
	protected EObject handleNotMatchedOld(EObject currentParent, EObject notMatchedOld) {
		Copier copier = myConfig.getCopier(notMatchedOld.eClass());
		return copier.copyToCurrent(currentParent, notMatchedOld);
	}

	public void reconcileResource(Resource current, Resource old){
		reconcileContents(null, current.getContents(), old.getContents());
	}
	
	public void reconcileTree(EObject currentRoot, EObject oldRoot){
		reconcileVertex(currentRoot, oldRoot);
		reconcileContents(currentRoot, currentRoot.eContents(), oldRoot.eContents());
	}
	
	private void reconcileVertex(EObject current, EObject old){
		assert current.eClass().equals(old.eClass());
		DecisionMaker[] decisionMakers = myConfig.getDecisionMakers(current.eClass());
		for (int i = 0; i < decisionMakers.length; i++){
			DecisionMaker next = decisionMakers[i];
			Decision decision = next.makeDecision(current, old);
			decision.apply(current, old, next.getFeature());
		}
	}
	
	private void reconcileContents(EObject currentParent, Collection allCurrents, Collection allOlds){
		if (allCurrents.isEmpty() && allOlds.isEmpty()){
			return;
		}
		List storage = myStoragePool.acquireList();
		myMatchingSession.match(allCurrents, allOlds, storage);
		
		for (Iterator pairs = storage.iterator(); pairs.hasNext();){
			Pair next = (Pair)pairs.next();
			EObject nextCurrent = next.current;
			EObject nextOld = next.old;
			assert (nextCurrent != null || nextOld != null);
			
			myMatchingSession.releasePair(next);
			
			if (nextCurrent == null){
				if (currentParent != null){ //never copy top-level resource contents
					nextCurrent = handleNotMatchedOld(currentParent, nextOld);
				} 
			}

			if (nextCurrent != null && nextOld != null){
				reconcileTree(nextCurrent, nextOld);
			} else if (nextOld == null){
				handleNotMatchedCurrent(nextCurrent);
			}
		}
		myStoragePool.release(storage);
	}
	
	private static class Pair {
		public EObject current;
		public EObject old;
		
		public void reset(){
			current = null;
			old = null;
		}
	}
	
	private class MatchingSession {
		private final Collection myCurrents;
		private final Collection myOlds;
		private final PairsPool myPool;
		private boolean myIsMatching;
		
		public MatchingSession(PairsPool pool){
			myPool = pool;
			myCurrents = new LinkedList();
			myOlds = new LinkedHashSet();
		}
		
		public void match(Collection currents, Collection olds, Collection output){
			assert !myIsMatching;
			assert myOlds.isEmpty();
			assert myCurrents.isEmpty();
			
			if (myIsMatching){
				throw new IllegalStateException("FIXME: remove me");
			}
			
			try {
				myIsMatching = true;

				myCurrents.addAll(currents);
				myOlds.addAll(olds);
				
				for (Iterator currentContents = myCurrents.iterator(); !myOlds.isEmpty() && currentContents.hasNext();){
					EObject nextCurrent = (EObject) currentContents.next();
					Pair nextPair = acquirePair();
					nextPair.current = nextCurrent;
					nextPair.old = removeMatched(nextCurrent, myOlds);
					output.add(nextPair);
					currentContents.remove();
				}
				
				for (Iterator notMatchedOlds = myOlds.iterator(); notMatchedOlds.hasNext();){
					Pair nextPair = acquirePair();
					nextPair.current = null;
					nextPair.old = (EObject)notMatchedOlds.next();
					output.add(nextPair);
				}
			} finally {
				myIsMatching = false;
				myCurrents.clear();
				myOlds.clear();
			}
		}
		
		private EObject removeMatched(EObject current, Collection allOld){
			EClass eClass = current.eClass();
			Matcher matcher = myConfig.getMatcher(eClass);
			EObject result = null;
			if (matcher != Matcher.FALSE){
				for (Iterator all = allOld.iterator(); all.hasNext();){
					EObject next = (EObject)all.next();
					if (eClass.equals(next.eClass()) && matcher.match(current, next)){
						result = next;
						all.remove();
						break;
					}
				}
			}
			return result;
		}

		private Pair acquirePair(){
			return myPool.acquire();
		}
		
		public void releasePair(Pair pair){
			myPool.release(pair);
		}
		
	}
	
	private static class PairsPool extends AbstractPool {
		public PairsPool(int capacity) {
			super(capacity);
		}
		
		public Pair acquire(){
			return (Pair)internalAcquire();
		}
		
		public void release(Pair pair){
			pair.current = null;
			pair.old = null;
			internalRelease(pair);
		}
		
		protected Object createNew() {
			return new Pair();
		}
	}
	
	private static class StoragePool extends AbstractPool {
		public StoragePool(int capacity){
			super(capacity);
		}
		
		public List acquireList(){
			return (List)internalAcquire();
		}
		
		public void release(List list){
			list.clear();
			internalRelease(list);
		}
		
		protected Object createNew() {
			return new ArrayList();
		}
	}

}