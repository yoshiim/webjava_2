package chara;

public class PlayerAx extends PlayerBase {

  /**
   * コンストラクタ.
   */
  public PlayerAx() {
    setName("斧使い");
    setHitPoint(100);
    setAttack(20);
    setDefense(10);
    setMagicAttack(5);
    setMagicDefense(5);
    setMaxHitPoint(100);
  }

  @Override
  public String getJob() {
    return "剣士";
  }

}
