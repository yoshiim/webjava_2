package chara;

import util.Utils;

public abstract class PlayerBase extends Character {

  /**
   * 職種取得.
   * @return 職種.
   */
  public abstract String getJob();

  /**
   * パラメータ表示.
   */
  public void showParamater() {
    Utils.println("パラメータ");
    Utils.println("　　名　前　：" + getName());
    Utils.println("　　職　業　：" + getJob());
    Utils.println("　　Ｈ　Ｐ　：" + getHitPoint());
    Utils.println("　攻　撃　力：" + getAttack());
    Utils.println("　防　御　力：" + getDefense());
    Utils.println("　魔法攻撃力：" + getMagicAttack());
    Utils.println("　魔法防御力：" + getMagicDefense());
  }
}
