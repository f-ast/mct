/*
 * Copyright 2006 Abdulla G. Abdurakhmanov (abdulla.abdurakhmanov@gmail.com).
 * 
 * Licensed under the LGPL, Version 2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.gnu.org/copyleft/lgpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * With any your questions welcome to my e-mail 
 * or blog at http://abdulla-a.blogspot.com.
 */

package test.org.ofsm;

import java.io.InputStream;

import junit.framework.TestCase;
import org.ofsm.*;


public class FiniteStateMachineTest extends TestCase {
    public FiniteStateMachineTest(String sTestName) {
        super(sTestName);
    }
    
    public void testMachine() {
        try {
            IFiniteStateMachine machine = FiniteStateMachineFactory.getInstance().newBuilder().getMachine();
            assertNotNull(machine);
            InputStream stream = getClass().getResourceAsStream("/test/org/ofsm/test.ofsm.xml");
            machine = FiniteStateMachineFactory.getInstance().newBuilder().getMachine("test.ofsm.xml",stream);
            assertNotNull(machine);
            assertNotNull(machine.fireEvent("TestEvent"));
            assertNotNull(machine.getCurrentState());
            assertEquals(machine.getCurrentState().getName(),"Fail");
            assertNotNull(machine.fireEvent("TestEvent"));
            assertNotNull(machine.getCurrentState());
            assertEquals(machine.getCurrentState().getName(),"Fail");
            assertNotNull(machine.fireEvent("TestEvent2"));
            assertNotNull(machine.getCurrentState());
            assertEquals(machine.getCurrentState().getName(),"Success");
            assertNotNull(machine.fireEvent("TestEvent2"));
            assertNotNull(machine.getCurrentState());
            assertEquals(machine.getCurrentState().getName(),"Success");
            assertNotNull(machine.fireEvent("TestEvent"));
            assertNotNull(machine.getCurrentState());
            assertEquals(machine.getCurrentState().getName(),"Fail");
            assertNull(machine.fireEvent("UnknownTestEvent"));
            assertNull(machine.getCurrentState());         
        }
        catch(Exception ex) {
            fail(ex.getMessage());
        }
    }
    
}