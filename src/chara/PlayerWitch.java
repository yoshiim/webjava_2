package chara;

public class PlayerWitch extends PlayerBase {

  /**
   * コンストラクタ.
   */
  public PlayerWitch() {
    setName("魔法使い");
    setHitPoint(100);
    setAttack(5);
    setDefense(10);
    setMagicAttack(15);
    setMagicDefense(10);
    setMaxHitPoint(100);
  }

  @Override
  public String getJob() {
    return "魔法使い";
  }

}
