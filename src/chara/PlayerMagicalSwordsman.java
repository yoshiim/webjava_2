package chara;

public class PlayerMagicalSwordsman extends PlayerBase {

  /**
   * コンストラクタ.
   */
  public PlayerMagicalSwordsman() {
    setName("魔法剣士");
    setHitPoint(100);
    setAttack(15);
    setDefense(15);
    setMagicAttack(5);
    setMagicDefense(5);
    setMaxHitPoint(100);
  }

  @Override
  public String getJob() {
    return "魔法剣士";
  }

}
