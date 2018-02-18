package chara;

public class EnemySkeleton extends EnemyBase {

  /**
   * コンストラクタ.
   */
  public EnemySkeleton() {
    setName("ガイコツ");
    setHitPoint(100);
    setAttack(15);
    setDefense(10);
    setMagicAttack(5);
    setMagicDefense(10);
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
