package fr.umlv.smalljs.ast;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class While extends AbstractExpr implements Instr{
  private final Expr condition;
  private final Block block;

  While(Expr condition, Block block) {
    super(condition.getLineNumber());
    this.condition = Objects.requireNonNull(condition);
    this.block = requireNonNull(block);
  }

  public Expr getCondition() {
    return condition;
  }
  public Block getBlock() {
    return block;
  }
}
