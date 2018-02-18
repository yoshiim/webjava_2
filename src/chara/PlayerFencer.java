package chara;

public class PlayerFencer extends PlayerBase {

  /**
   * コンストラクタ.
   */
  public PlayerFencer() {
    setName("剣士");
    setHitPoint(100);
    setAttack(15);
    setDefense(10);
    setMagicAttack(5);
    setMagicDefense(10);
  }

  @Override
  public int getMaxHitPoint() {
    return 100;
  }

  @Override
  public String getJob() {
    return "剣士";
  }

}
