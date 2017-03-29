package fr.umlv.smalljs.grammar.tools;

import fr.umlv.tatoo.runtime.tools.ToolsTable;

import java.util.EnumMap;
import java.util.EnumSet;

import fr.umlv.smalljs.grammar.lexer.RuleEnum;
import fr.umlv.smalljs.grammar.parser.TerminalEnum;

public class ToolsDataTable {
  public static ToolsTable<RuleEnum,TerminalEnum> createToolsTable() {
      EnumSet<RuleEnum> spawns = EnumSet.of(RuleEnum.dot,RuleEnum.id,RuleEnum.semicolon,RuleEnum.mul,RuleEnum.function,RuleEnum.eq,RuleEnum.lcurl,RuleEnum.rcurl,RuleEnum.colon,RuleEnum.rem,RuleEnum._while,RuleEnum._return,RuleEnum._if,RuleEnum.text,RuleEnum.comment,RuleEnum.ge,RuleEnum.rpar,RuleEnum._else,RuleEnum.ne,RuleEnum.assign,RuleEnum.var,RuleEnum.gt,RuleEnum.integer,RuleEnum.div,RuleEnum.sub,RuleEnum.add,RuleEnum.eol,RuleEnum.lpar,RuleEnum.comma,RuleEnum.lt,RuleEnum.le);
      EnumSet<RuleEnum> discards = EnumSet.allOf(RuleEnum.class);
      EnumMap<RuleEnum,TerminalEnum> terminal = new EnumMap<RuleEnum,TerminalEnum>(RuleEnum.class);
              terminal.put(RuleEnum.dot,TerminalEnum.dot);
              terminal.put(RuleEnum.id,TerminalEnum.id);
              terminal.put(RuleEnum.semicolon,TerminalEnum.semicolon);
              terminal.put(RuleEnum.mul,TerminalEnum.mul);
              terminal.put(RuleEnum.function,TerminalEnum.function);
              terminal.put(RuleEnum.eq,TerminalEnum.eq);
              terminal.put(RuleEnum.lcurl,TerminalEnum.lcurl);
              terminal.put(RuleEnum.rcurl,TerminalEnum.rcurl);
              terminal.put(RuleEnum.colon,TerminalEnum.colon);
              terminal.put(RuleEnum.rem,TerminalEnum.rem);
              terminal.put(RuleEnum._while,TerminalEnum._while);
              terminal.put(RuleEnum._return,TerminalEnum._return);
              terminal.put(RuleEnum._if,TerminalEnum._if);
              terminal.put(RuleEnum.text,TerminalEnum.text);
              terminal.put(RuleEnum.ge,TerminalEnum.ge);
              terminal.put(RuleEnum.rpar,TerminalEnum.rpar);
              terminal.put(RuleEnum._else,TerminalEnum._else);
              terminal.put(RuleEnum.ne,TerminalEnum.ne);
              terminal.put(RuleEnum.assign,TerminalEnum.assign);
              terminal.put(RuleEnum.var,TerminalEnum.var);
              terminal.put(RuleEnum.gt,TerminalEnum.gt);
              terminal.put(RuleEnum.integer,TerminalEnum.integer);
              terminal.put(RuleEnum.div,TerminalEnum.div);
              terminal.put(RuleEnum.sub,TerminalEnum.sub);
              terminal.put(RuleEnum.add,TerminalEnum.add);
              terminal.put(RuleEnum.eol,TerminalEnum.eol);
              terminal.put(RuleEnum.lpar,TerminalEnum.lpar);
              terminal.put(RuleEnum.comma,TerminalEnum.comma);
              terminal.put(RuleEnum.lt,TerminalEnum.lt);
              terminal.put(RuleEnum.le,TerminalEnum.le);
            EnumSet<RuleEnum> unconditionals = EnumSet.of(RuleEnum.comment,RuleEnum.space);
      return new ToolsTable<RuleEnum,TerminalEnum>(spawns,discards,unconditionals,terminal);
  }
}