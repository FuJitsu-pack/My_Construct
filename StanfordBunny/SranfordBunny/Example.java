package StanfordBunny;

/**
 * うさぎをOpenGLでレンダリング（描画）するプログラムである。
 * JOGL（Java bindings for OpenGL）の例題プログラムである。
 * OpenGLを使ったJavaによる三次元グラフィックスの初歩的な練習になる。
 * オブザーバ・デザインパターン（MVC: Model-View-Controller）を用いた典型的（模範的）なプログラムである。
 */
public class Example extends Object
{
    /**
     * OpenGLオブジェクトからOpenGLモデルを生成してウィンドウを開く。
     * @param arguments コマンドの引数列（文字列の配列）
     */
    public static void main(String[] arguments)
    {
        int x = 100;
        int y = 100;
        
        // うさぎを生成してウィンドウを開く。
        StanfordBunny.open(x += 25, y += 25);
        
        return;
    }
}
