package battle;

import chara.Character;
import chara.EnemyBase;
import chara.PlayerBase;

import stage.StageBase;

import util.ScannerManager;
import util.Utils;

public class Battle {

  private PlayerBase player;
  private StageBase stage;

  /**
   * コンストラクタ.
   * @param player プレイヤー.
   * @param stage ステージ.
   */
  Battle(final PlayerBase player, final StageBase stage) {
    this.player = player;
    this.stage = stage;
  }

  /**
   * 実行処理.
   */
  void run() {
    ScannerManager scanner = ScannerManager.getInstance();
    int beforeCount = -1;
    for (int count = 0; count < stage.getEnemyList().size();) {
      EnemyBase enemy = stage.getEnemyList().get(count);
      if (beforeCount != count) {
        Utils.print("\n\n■■■■■■■■■■■■■■■■■■\n\n");
        Utils.print("第" + (count + 1) + "戦（全" + stage.getEnemyList().size() + "戦）\n\n");

        Utils.println(enemy.getName() + "が現れた！\n");
        Utils.println("■■■■■■■■■■■■■■■■■■\n");
      }
      beforeCount = count;

      showHitPoint(enemy);

      String input;
      do {
        Utils.println("\n\nあなたのターン！何をしますか？");
        Utils.println("　1：攻撃");
        Utils.println("　2：魔法攻撃");
        Utils.println("　3：回復アイテムを使う");
        Utils.println("　4：逃げる");
        input = scanner.getInput();
      } while (!Utils.isInputOk(input, new String[] {"1","2","3","4"}));

      switch (input) {
        case "1":
          Utils.println("\nあなたの攻撃！");
          battleInternal(player, enemy, false, true);
          break;
        case "2":
          Utils.println("\nあなたの魔法攻撃！");
          battleInternal(player, enemy, true, true);
          break;
        case "3":
          // 30の0.5～1.5倍分回復する.
          int recovery = (int) (30 * (Math.random() + 0.5));
          Utils.println("\nあなたはHPを" + recovery + "回復した");
          if (player.getHitPoint() + recovery >= player.getMaxHitPoint()) {
            player.setHitPoint(player.getMaxHitPoint());
            Utils.println("\n" + player.getName() + "のHPは満タンになった");
          } else {
            player.setHitPoint(player.getHitPoint() + recovery);
          }
          break;
        case "4":
          // ランダムで勝ちになる.
          if (tryToRunAway()) {
            // 成功した場合.
            // 相手のHPも0にしておく.
            enemy.setHitPoint(0);
            count++;
            continue;
          }

          break;
        default:
          break;
      }

      if (enemy.getHitPoint() <= 0) {
        Utils.sleep(1000);
        Utils.println("\n\n" + enemy.getName() + "を倒した！\n");
        // 敵のHPがなくなった場合.
        // 報酬を獲得により回復.
        int reward = (int)(enemy.getReward() * (Math.random() + 0.5));
        Utils.println(reward + "回復した");
        if (player.getHitPoint() + reward < player.getMaxHitPoint()) {
          player.setHitPoint(player.getHitPoint() + reward);
        } else {
          player.setHitPoint(player.getMaxHitPoint());
        }

        Utils.sleep(2000);
        count++;
        continue;
      }

      Utils.sleep(1000);

      showHitPoint(enemy);

      Utils.sleep(1500);

      Utils.println("\n\nあいての" + enemy.getName() + "のターン！\n");
      Utils.sleep(1000);
      boolean isMagic;
      if (enemy.getAttack() == enemy.getMagicAttack()) {
        // 攻撃力と魔法攻撃力が同じ場合はどちらで攻撃するかランダム.
        int random = (int) (Math.random() * 2);
        isMagic = random == 0;
      } else {
        isMagic = enemy.getMagicAttack() > enemy.getAttack();
      }
      Utils.print("あいての" + enemy.getName() + "の" + (isMagic ? "魔法攻撃" : "攻撃") + "！\n");
      battleInternal(enemy, player, isMagic, false);
      Utils.println("\n");

      Utils.sleep(1000);

      if (player.getHitPoint() <= 0) {
        //プレイヤーのHPがなくなった場合.
        Utils.println("\n" + enemy.getName() + "に倒されてしまった！\n");
        Utils.sleep(2000);
        break;
      }
    }
  }

  /**
   * バトル処理.
   * @param attackCharacter 攻撃キャラ.
   * @param defenseCharacter 防御キャラ.
   * @param isMagic 魔法かどうか.
   * @param isPlayer プレイヤーかどうか.
   */
  private void battleInternal(
      final Character attackCharacter,
      final Character defenseCharacter,
      final boolean isMagic,
      final boolean isPlayer) {
    int attack = isMagic ? attackCharacter.getMagicAttack() : attackCharacter.getAttack();

    // 攻撃は1.5～2.0倍にする.
    attack = (int) (attack * (Math.random()  * 0.5 + 1.5));

    // プレイヤーの攻撃は1.5倍する.
    if (isPlayer) {
      attack = (int)(attack * 1.5);
    }

    // 10%の確率で急所.
    int random = (int) (Math.random() * 100);
    attack = random > 10 ? attack : attack * 2;

    int defense = isMagic ? defenseCharacter.getDefense() : defenseCharacter.getMagicDefense();

    // ダメージを計算.
    int damage = attack - defense;
    if (damage <= 0) {
      // 最低でも1ダメージ.
      damage = 1;
    }

    // 負にならないようにする.
    if (defenseCharacter.getHitPoint() - damage >= 0) {
      defenseCharacter.setHitPoint(defenseCharacter.getHitPoint() - damage);
    } else {
      defenseCharacter.setHitPoint(0);
    }

    for (int i = 0; i < 10; i++) {
      Utils.sleep(100);
      Utils.print("・");
    }

    Utils.println("\n" + damage + "ダメージ！");
  }

  /**
   * 逃げようとする処理.
   * @return true:成功 false:失敗.
   */
  private boolean tryToRunAway() {
    Utils.println("あなたは逃げようとした！\n");
    for (int i = 0; i < 10; i++) {
      Utils.sleep(100);
      Utils.print("・");
    }
    int random = (int) (Math.random() * 100);
    if (random % 5 == 0) {
      Utils.println("\n\nなんと成功した！");
      return true;
    } else {
      Utils.println("\n\n失敗した！\n");
      return false;
    }
  }

  /**
   * HP表示.
   * @param enemy 敵.
   */
  private void showHitPoint(final EnemyBase enemy) {
    Utils.println("\n自分のＨＰ：" + player.getHitPoint() + "/" + player.getMaxHitPoint());
    Utils.println("敵のＨＰ　：" + enemy.getHitPoint() + "/" + enemy.getMaxHitPoint());
  }
}
