package battle;

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
import util.Utils;

import java.util.ArrayList;

public class BattleHero {

  /**
   * メイン.
   * @param args 引数.
   */
  public static void main(String[] args) {
    // 処理開始.
    new BattleHero().start();
  }

  /**
   * バトルスタート.
   */
  private void start() {

    // プレイヤー選択.
    PlayerBase player = selectPlayer();

    // ステージ選択.
    StageBase stage = selectStage();

    // 難易度選択.
    selectDifficulty(stage);

    // バトル実行.
    new Battle(player, stage).run();

    // バトルが終わったら戻ってくる.
    // クリアチェック.
    boolean isClear = true;
    for (EnemyBase check : stage.getEnemyList()) {
      if (check.getHitPoint() > 0) {
        // 0以上の場合はゲームオーバー.
        isClear = false;
        break;
      }
    }

    Utils.println("\n\n★★★★★★★★★★★★★★★★★★★★★★");
    Utils.print("\n　");
    if (isClear) {
      Utils.println("クリアしました！！おめでとう！！");
    } else {
      Utils.println("げ～むお～ば～");
    }
    Utils.println("\n★★★★★★★★★★★★★★★★★★★★★★");

    // 終了処理.
    ScannerManager.getInstance().finish();
  }

  /**
   * ステージ選択.
   * @return ステージ.
   */
  private StageBase selectStage() {
    StageBase stage = null;
    do {
      Utils.println("\nどのステージで遊びますか？");
      Utils.println("　1:3連戦ステージ");
      Utils.println("　2:5連戦ステージ");
      Utils.println("　3:10連戦ステージ");
      String stageInput = ScannerManager.getInstance().getInput();
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
          Utils.printInputError();
          break;
      }
    } while (stage == null);
    return stage;
  }

  /**
   * プレイヤー選択処理.
   * @return プレイヤー.
   */
  private PlayerBase selectPlayer() {
    PlayerBase player = null;
    do {
      Utils.println("\nあなたは誰ですか？");
      Utils.println("　1:剣士");
      Utils.println("　2:魔法使い");
      Utils.println("　3:斧使い");
      Utils.println("　4:魔法剣士");
      String input = ScannerManager.getInstance().getInput();
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
          Utils.printInputError();
          break;
      }

      if (player != null) {
        // パラメータ表示.
        player.showParamater();

        String check = null;
        do {
          Utils.println("\n本当によろしいですか？（y:はい　n:いいえ）");
          check = ScannerManager.getInstance().getInput();
        } while (!Utils.isInputOk(check, new String[] {"y","n"}));
        if (check.equals("n")) {
          player = null;
        }
      }
    } while (player == null);
    return player;
  }

  /**
   * 難易度選択.
   * @param stage 選択中ステージ.
   */
  private void selectDifficulty(final StageBase stage) {
    double magnification = 0;
    do {
      Utils.println("\nどの難易度で遊びますか？");
      Utils.println("　1:簡単");
      Utils.println("　2:普通");
      Utils.println("　3:難しい");
      String stageInput = ScannerManager.getInstance().getInput();
      if (stageInput == null) {
        continue;
      }
      switch (stageInput) {
        case "1":
          magnification = 0.9;
          break;
        case "2":
          magnification = 1;
          break;
        case "3":
          magnification = 1.1;
          break;
        default:
          Utils.printInputError();
          break;
      }
    } while (magnification == 0);

    // 難易度の倍率、敵のパラメータを変化させる.
    ArrayList<EnemyBase> enemyList = stage.getEnemyList();
    for (EnemyBase enemy : enemyList) {
      enemy.setAttack((int) (enemy.getAttack() * magnification));
      enemy.setDefense((int) (enemy.getDefense() * magnification));
      enemy.setHitPoint((int) (enemy.getHitPoint() * magnification));
      enemy.setMaxHitPoint((int) (enemy.getMaxHitPoint() * magnification));
      enemy.setMagicAttack((int) (enemy.getMagicAttack() * magnification));
      enemy.setMagicDefense((int) (enemy.getMagicDefense() * magnification));
    }
  }
}
