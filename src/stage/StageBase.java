package stage;

import chara.EnemyBase;

import java.util.ArrayList;

public abstract class StageBase {
  protected ArrayList<EnemyBase> enemyList;

  public ArrayList<EnemyBase> getEnemyList() {
    return enemyList;
  }
}
