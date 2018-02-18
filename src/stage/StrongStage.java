package stage;

import chara.EnemyDemon;
import chara.EnemyGoblin;
import chara.EnemySkeleton;

import java.util.ArrayList;

public class StrongStage extends StageBase {

  /**
   * コンストラクタ.
   */
  public StrongStage() {
    enemyList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
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
