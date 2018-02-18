package battle;

import chara.Character;
import chara.EnemyBase;
import chara.PlayerAx;
import chara.PlayerBase;
import chara.PlayerFencer;
import chara.PlayerMagicalSwordsman;
import chara.PlayerWitch;

import stage.NormalStage;
import stage.StageBase;
import stage.StrongStage;
import stage.WeakStage;

import util.ScannerManager;

public class BattleHero {

  /**
   * メイン.
   * @param args 引数.
   */
  public static void main(String[] args) {

    ScannerManager scanner = ScannerManager.getInstance();

    StageBase stage = null;
    do {
      println("\nどのステージで遊びますか？");
      println("　1:弱いステージ（3戦）");
      println("　2:普通ステージ（5戦）");
      println("　3:強いステージ（10戦）");
      String stageInput = scanner.getInput();
      if (stageInput == null) {
        continue;
      }
      switch (stageInput) {
        case "1":
          stage = new WeakStage();
          break;
        case "2":
          stage = new NormalStage();
          break;
        case "3":
          stage = new StrongStage();
          break;
        default:
          printInputError();
          break;
      }
    } while (stage == null);


    PlayerBase player = null;
    do {
      println("\n\nあなたは誰ですか？");
      println("　1:剣士");
      println("　2:魔法使い");
      println("　3:斧使い");
      println("　4:魔法剣士");
      String input = scanner.getInput();
      if (input == null) {
        continue;
      }
      switch (input) {
        case "1":
          player = new PlayerFencer();
          break;
        case "2":
          player = new PlayerWitch();
          break;
        case "3":
          player = new PlayerAx();
          break;
        case "4":
          player = new PlayerMagicalSwordsman();
          break;
        default:
          printInputError();
          break;
      }

      if (player != null) {
        println("パラメータ");
        println("　　名　前　：" + player.getName());
        println("　　職　業　：" + player.getJob());
        println("　　Ｈ　Ｐ　：" + player.getHitPoint());
        println("　攻　撃　力：" + player.getAttack());
        println("　防　御　力：" + player.getDefense());
        println("　魔法攻撃力：" + player.getMagicAttack());
        println("　魔法防御力：" + player.getMagicDefense());

        String check = null;
        do {
          println("\n本当によろしいですか？（y:はい　n:いいえ）");
          check = scanner.getInput();
        } while (!isInputOk(check, new String[] {"y","n"}));
        if (check.equals("n")) {
          player = null;
        }
      }
    } while (player == null);

    int beforeCount = -1;
    for (int count = 0; count < stage.getEnemyList().size();) {
      EnemyBase enemy = stage.getEnemyList().get(count);
      if (beforeCount != count) {
        print("\n\n■■■■■■■■■■■■■■■■■■\n\n");
        print("第" + (count + 1) + "戦（全" + stage.getEnemyList().size() + "戦）\n\n");

        println(enemy.getName() + "が現れた！\n");
        println("■■■■■■■■■■■■■■■■■■\n");
      }
      beforeCount = count;

      println("自分のＨＰ：" + player.getHitPoint() + "/" + player.getMaxHitPoint());
      println("敵のＨＰ　：" + enemy.getHitPoint() + "/" + enemy.getMaxHitPoint());

      String input;
      do {
        println("\n\nあなたのターン！何をしますか？");
        println("　1：攻撃");
        println("　2：魔法攻撃");
        println("　3：回復アイテムを使う");
        println("　4：逃げる");
        input = scanner.getInput();
      } while (!isInputOk(input, new String[] {"1","2","3","4"}));

      switch (input) {
        case "1":
          println("\nあなたの攻撃！");
          battle(player, enemy, false, true);
          break;
        case "2":
          println("\nあなたの魔法攻撃！");
          battle(player, enemy, true, true);
          break;
        case "3":
          // 30の0.5～1.5倍分回復する.
          int recovery = (int) (30 * (Math.random() + 0.5));
          println("\nあなたはHPを" + recovery + "回復した");
          if (player.getHitPoint() + recovery >= player.getMaxHitPoint()) {
            player.setHitPoint(player.getMaxHitPoint());
            println("\n" + player.getName() + "のHPは満タンになった");
          } else {
            player.setHitPoint(player.getHitPoint() + recovery);
          }
          break;
        case "4":
          // ランダムで勝ちになる.
          println("あなたは逃げようとした！\n");
          for (int i = 0; i < 10; i++) {
            sleep(100);
            print("・");
          }
          int random = (int) (Math.random() * 100);
          if (random % 5 == 0) {
            println("\n\nなんと成功した！");
            // 5%の確率で成功.
            // 相手のHPも0にしておく.
            enemy.setHitPoint(0);
            count++;
            continue;
          } else {
            println("\n\n失敗した！\n");
          }

          break;
        default:
          break;
      }

      if (enemy.getHitPoint() <= 0) {
        sleep(1000);
        println("\n\n" + enemy.getName() + "を倒した！\n");
        // 敵のHPがなくなった場合.
        // 報酬を獲得により回復.
        int reward = (int)(enemy.getReward() * (Math.random() + 0.5));
        println(reward + "回復した");
        if (player.getHitPoint() + reward < player.getMaxHitPoint()) {
          player.setHitPoint(player.getHitPoint() + reward);
        } else {
          player.setHitPoint(player.getMaxHitPoint());
        }

        sleep(2000);
        count++;
        continue;
      }

      sleep(1000);

      println("\n自分のＨＰ：" + player.getHitPoint() + "/" + player.getMaxHitPoint());
      println("敵のＨＰ　：" + enemy.getHitPoint() + "/" + enemy.getMaxHitPoint());
      sleep(1500);

      println("\n\nあいての" + enemy.getName() + "のターン！\n");
      sleep(1000);
      boolean isMagic;
      if (enemy.getAttack() == enemy.getMagicAttack()) {
        // 攻撃力と魔法攻撃力が同じ場合はどちらで攻撃するかランダム.
        int random = (int) (Math.random() * 2);
        isMagic = random == 0;
      } else {
        isMagic = enemy.getMagicAttack() > enemy.getAttack();
      }
      print("あいての" + enemy.getName() + "の" + (isMagic ? "魔法攻撃" : "攻撃") + "！\n");
      battle(enemy, player, isMagic, false);
      println("\n");

      sleep(1000);

      if (player.getHitPoint() <= 0) {
        //プレイヤーのHPがなくなった場合.
        println("\n" + enemy.getName() + "に倒されてしまった！\n");
        sleep(2000);
        break;
      }

    }

    // クリアチェック.
    boolean isClear = true;
    for (EnemyBase check : stage.getEnemyList()) {
      if (check.getHitPoint() > 0) {
        // 0以上の場合はゲームオーバー.
        isClear = false;
        break;
      }
    }

    println("\n\n★★★★★★★★★★★★★★★★★★★★★★");
    print("\n　");
    if (isClear) {
      println("クリアしました！！おめでとう！！");
    } else {
      println("げ～むお～ば～");
    }
    println("\n★★★★★★★★★★★★★★★★★★★★★★");

    // 終了処理.
    scanner.finish();
  }

  /**
   * バトル処理.
   * @param attackCharacter 攻撃キャラ.
   * @param defenseCharacter 防御キャラ.
   * @param isMagic 魔法かどうか.
   * @param isPlayer プレイヤーかどうか.
   */
  private static void battle(
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
      sleep(100);
      print("・");
    }

    println("\n" + damage + "ダメージ！");
  }

  /**
   * 文字出力（改行あり）.
   * @param text 出力文字.
   */
  private static void println(final String text) {
    System.out.println(text);
  }

  /**
   * 文字出力（改行なし）.
   * @param text 出力文字.
   */
  private static void print(final String text) {
    System.out.print(text);
  }

  private static void printInputError() {
    println("\n正しい値を入力してください");
  }

  /**
   * 入力値チェック.
   * @param input 入力文字.
   * @param checkStrings チェック文字配列.
   * @return true:OK false:NG.
   */
  private static boolean isInputOk(final String input, final String[] checkStrings) {
    for (String string : checkStrings) {
      if (string.equals(input)) {
        return true;
      }
    }
    printInputError();
    return false;
  }

  /**
   * 指定時間停止.
   * @param stop ミリ秒.
   */
  private static void sleep(final int stop) {
    try {
      Thread.sleep(stop);
    } catch (InterruptedException e){}
  }

}
