package stage;

import chara.EnemyGoblin;
import chara.EnemySkeleton;
import chara.EnemyDemon;

import java.util.ArrayList;

public class WeakStage extends StageBase {

  /**
   * コンストラクタ.
   */
  public WeakStage() {
    enemyList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      int random = (int)(Math.random() * 3);
      switch (random) {
        case 0:
          enemyList.add(new EnemySkeleton());
          break;
        case 1:
          enemyList.add(new EnemyDemon());
          break;
        case 2:
          enemyList.add(new EnemyGoblin());
          break;
        default:
          break;
      }
    }
  }
}
