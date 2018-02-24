package util;

public class Utils {

  /**
   * インスタンス化禁止.
   */
  private Utils() {
  }


  /**
   * 文字出力（改行あり）.
   * @param text 出力文字.
   */
  public static void println(final String text) {
    System.out.println(text);
  }

  /**
   * 文字出力（改行なし）.
   * @param text 出力文字.
   */
  public static void print(final String text) {
    System.out.print(text);
  }

  public static void printInputError() {
    println("\n正しい値を入力してください");
  }

  /**
   * 入力値チェック.
   * @param input 入力文字.
   * @param checkStrings チェック文字配列.
   * @return true:OK false:NG.
   */
  public static boolean isInputOk(final String input, final String[] checkStrings) {
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
  public static void sleep(final int stop) {
    try {
      Thread.sleep(stop);
    } catch (InterruptedException e){
      // 何もしない.
    }
  }
}
