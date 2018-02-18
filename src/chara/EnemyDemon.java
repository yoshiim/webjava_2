package chara;

public class EnemyDemon extends EnemyBase {

  /**
   * コンストラクタ.
   */
  public EnemyDemon() {
    setName("悪魔");
    setHitPoint(100);
    setAttack(5);
    setDefense(5);
    setMagicAttack(15);
    setMagicDefense(15);
  }

  @Override
  public int getMaxHitPoint() {
    // TODO 自動生成されたメソッド・スタブ
    return 100;
  }

  @Override
  public int getReward() {
    // TODO 自動生成されたメソッド・スタブ
    return 50;
  }

}
