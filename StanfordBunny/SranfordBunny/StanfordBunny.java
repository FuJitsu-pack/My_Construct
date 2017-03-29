package StanfordBunny;


import java.util.ArrayList;
import java.util.Iterator;

import mvc4jogl.OpenGLCompound;
import mvc4jogl.OpenGLModel;
import mvc4jogl.OpenGLObject;
import mvc4jogl.OpenGLProjection;
import mvc4jogl.OpenGLTriangle;

import utility.StringUtility;

/**
 * うさぎをレンダリング（描画）する。<br>
 * XYZ軸に加えてうさぎを描画する。<br>
 * 視界（視点・注視点・上方向ベクトル・視界角・近・遠）もドラゴンが応答する。<br>
 */
public class StanfordBunny extends OpenGLCompound
{
	public StanfordBunny()
	{
        super();
        String urlString = "StanfordBunny.ply";
        ArrayList<String> aCollection = StringUtility.readTextFromFile(urlString);
		int numberOfVertexes = 0;
		int numberOfTriangles = 0;
		Iterator anIterator = aCollection.iterator();
		 while (anIterator.hasNext()){
                     String aString = (String)anIterator.next();
                     ArrayList<String> aList = StringUtility.splitString(aString, " \t\n\r");
                     if (aList.size() == 0) { continue; }
                     String firstString = aList.get(0);
                     if (firstString.compareTo("element") == 0){
                       String secondString = aList.get(1);
                         if (secondString.compareTo("vertex") == 0){
                            numberOfVertexes = Integer.valueOf(aList.get(2));
                         }
                     
                        if(secondString.compareTo("face") == 0){
                            numberOfTriangles = Integer.valueOf(aList.get(2));
                        }
                     }
     
                     if (firstString.compareTo("end_header") == 0){
                        System.out.println("firstString = " + firstString);
                         double[][] vertexArray = new double[numberOfVertexes][3];
                         for (int n = 0; n < numberOfVertexes; n++){
                             aString = (String)anIterator.next();
                             aList = StringUtility.splitString(aString, " \t\n\r");
                             double x = Double.valueOf(aList.get(0)) * 18.0d + 0.5d;
                             double y = Double.valueOf(aList.get(1)) * 18.0d - 1.5d;
                             double z = Double.valueOf(aList.get(2)) * 18.0d;
                             vertexArray[n][0] = x;
                             vertexArray[n][1] = y;
                             vertexArray[n][2] = z;
                         }

                         for (int n = 0; n < numberOfTriangles; n++){
                             aString = (String)anIterator.next();
                             aList = StringUtility.splitString(aString, " \t\n\r");
                             int i = Integer.valueOf(aList.get(1));
                             int j = Integer.valueOf(aList.get(2));
                             int k = Integer.valueOf(aList.get(3));
                             double[] vertex1 = vertexArray[i];
                             double[] vertex2 = vertexArray[j];
                             double[] vertex3 = vertexArray[k];
                             OpenGLTriangle aTriangle = new OpenGLTriangle(vertex1,vertex2,vertex3);
                             aTriangle.rgb(1.0d, 0.9d, 0.8d);
                             this.add(aTriangle);
                             
                         }
                     }
                 }
             return;
         }

    /**
     * うさぎを生成してウィンドウを開く。
     * @param arguments コマンドの引数列（文字列の配列）
     */
    public static void main(String[] arguments)
    {
        StanfordBunny.open(150, 150);
    }

        /**
         * うさぎを生成してウィンドウを開く。
         * @param x ウィンドウを開く場所のx座標
         * @param y ウィンドウを開く場所のy座標
         */
         public static void open(int x, int y)
         {
             // 描画オブジェクトを生成する。
             OpenGLObject aBunny = new StanfordBunny();
           
             // モデルに描画オブジェクトを指定してウィンドウを開く。
             OpenGLModel  aModel = new OpenGLModel(aBunny);
             aModel.axesScale(1.0d);
             aModel.windowTitle("StanfordBunny");
             OpenGLProjection aProjection = aModel.projection();
             aProjection.eyePoint(-5.5852450791872, 3.07847342734, 15.794105252496);
             aProjection.sightPoint(0.27455347776413, 0.20096999406815, -0.11261999607086);
             aProjection.upVector(0.1018574904194, 0.98480906061847, -0.14062775604137);
             aProjection.fovy(12.642721790235);
             aModel.open(x, y);

             return;
         }
    
}
