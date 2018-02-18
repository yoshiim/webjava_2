package util;

import java.util.Scanner;

/**
 * 入力管理クラス.
 *
 */
public class ScannerManager {
  /** インスタンス. */
  private static ScannerManager sScannerManager = null;
  /** スキャナ. */
  private Scanner scanner;
  /** closeフラグ. */
  private boolean isClose = false;

  /**
   * コントラクタ.
   */
  private ScannerManager() {
    scanner = new Scanner(System.in);
  }

  /**
   * インスタンス取得.
   * @return インスタンス.
   */
  public static ScannerManager getInstance() {
    if (sScannerManager == null) {
      sScannerManager = new ScannerManager();
    }
    return sScannerManager;
  }

  /**
   * 入力取得.
   * @return 入力された文字
   *          close済みの場合nullを返却.
   */
  public String getInput() {

    if (isClose) {
      return null;
    }

    String input = scanner.next();
    return input;
  }

  /**
   * 終了処理.
   * ※プログラム終了時にコールすること.
   */
  public void finish() {
    scanner.close();
    isClose = true;
  }
}
