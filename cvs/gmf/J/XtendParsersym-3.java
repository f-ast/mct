/*******************************************************************************
* Copyright (c) 2006 Eclipse.org
* 
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.eclipse.gmf.internal.xpand.xtend.parser;

public interface XtendParsersym {
    public final static int
      TK_IDENT = 1,
      TK_STRING = 7,
      TK_INT_CONST = 17,
      TK_REAL_CONST = 18,
      TK_let = 27,
      TK_switch = 24,
      TK_implies = 35,
      TK_new = 19,
      TK_false = 20,
      TK_true = 21,
      TK_null = 22,
      TK_default = 36,
      TK_case = 37,
      TK_Collection = 3,
      TK_List = 4,
      TK_Set = 5,
      TK_typeSelect = 8,
      TK_collect = 9,
      TK_select = 10,
      TK_reject = 11,
      TK_exists = 12,
      TK_notExists = 13,
      TK_forAll = 14,
      TK_QUESTION_MARK = 38,
      TK_DCOLON = 28,
      TK_COLON = 26,
      TK_LPAREN = 2,
      TK_RPAREN = 15,
      TK_LCURLY = 16,
      TK_RCURLY = 29,
      TK_LSQUARE = 32,
      TK_RSQUARE = 33,
      TK_ARROW = 39,
      TK_NOT = 6,
      TK_AND = 40,
      TK_OR = 41,
      TK_ASSIGN = 42,
      TK_EQ = 43,
      TK_NE = 44,
      TK_GE = 45,
      TK_LE = 46,
      TK_GT = 47,
      TK_LT = 48,
      TK_PLUS = 49,
      TK_MINUS = 23,
      TK_MULTI = 50,
      TK_DIV = 51,
      TK_DOT = 30,
      TK_COMMA = 31,
      TK_BAR = 52,
      TK_import = 53,
      TK_extension = 54,
      TK_reexport = 55,
      TK_private = 56,
      TK_create = 57,
      TK_cached = 58,
      TK_JAVA = 59,
      TK_GLOBALVAR = 60,
      TK_SEMI = 25,
      TK_EOF_TOKEN = 34,
      TK_ERROR_TOKEN = 61;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "IDENT",
                 "LPAREN",
                 "Collection",
                 "List",
                 "Set",
                 "NOT",
                 "STRING",
                 "typeSelect",
                 "collect",
                 "select",
                 "reject",
                 "exists",
                 "notExists",
                 "forAll",
                 "RPAREN",
                 "LCURLY",
                 "INT_CONST",
                 "REAL_CONST",
                 "new",
                 "false",
                 "true",
                 "null",
                 "MINUS",
                 "switch",
                 "SEMI",
                 "COLON",
                 "let",
                 "DCOLON",
                 "RCURLY",
                 "DOT",
                 "COMMA",
                 "LSQUARE",
                 "RSQUARE",
                 "EOF_TOKEN",
                 "implies",
                 "default",
                 "case",
                 "QUESTION_MARK",
                 "ARROW",
                 "AND",
                 "OR",
                 "ASSIGN",
                 "EQ",
                 "NE",
                 "GE",
                 "LE",
                 "GT",
                 "LT",
                 "PLUS",
                 "MULTI",
                 "DIV",
                 "BAR",
                 "import",
                 "extension",
                 "reexport",
                 "private",
                 "create",
                 "cached",
                 "JAVA",
                 "GLOBALVAR",
                 "ERROR_TOKEN"
             };

    public final static boolean isValidForParser = true;
}