package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Group;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       final Group root =  new Group();//FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

/* todo Arc*/
        Arc arcOpen = new Arc();
        arcOpen.setFill(Color.KHAKI);
        arcOpen.setStroke(Color.OLIVE);
        arcOpen.setStrokeWidth(5);
        arcOpen.setStrokeLineCap(StrokeLineCap.ROUND);
        arcOpen.setStrokeType(StrokeType.OUTSIDE);
        arcOpen.setSmooth(true);
        arcOpen.setCenterX(50);
        arcOpen.setCenterY(50);
        arcOpen.setLength(130);
        arcOpen.setRadiusX(80);
        arcOpen.setRadiusY(50);
        arcOpen.setStartAngle(90);
        arcOpen.setType(ArcType.OPEN);

        Scene scene = new Scene(root, 400, 400);

        arcOpen.setCenterX(100);
        arcOpen.setCenterY(100);
        root.getChildren().add(arcOpen);

/*todo line */

        Line line = new Line();
        line.setStroke(Color.OLIVE);
        line.setStrokeWidth(3);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setStrokeType(StrokeType.OUTSIDE);
        line.getStrokeDashArray().addAll(5.0,10.0);
        line.setStrokeDashOffset(5);
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(100);
        line.setEndY(100);

        line.setStartX(150);
        line.setStartY(150);

        root.getChildren().add(line);



       /* todo circle*/

        Circle circle = new Circle();
        circle.setFill(Color.KHAKI);
        circle.setStroke(Color.OLIVE);
        circle.setStrokeWidth(5);
        circle.setStrokeType(StrokeType.OUTSIDE);
        circle.setCenterX(250);
        circle.setCenterY(60);
        circle.setRadius(50);

        root.getChildren().add(circle);


        /*todo CubicCurve*/
        CubicCurve cubic = new CubicCurve();
        cubic.setFill(Color.KHAKI);
        cubic.setStroke(Color.OLIVE);
        cubic.setStrokeWidth(3);
        cubic.setStrokeLineCap(StrokeLineCap.ROUND);
        cubic.setStrokeType(StrokeType.CENTERED);
        cubic.setStartX(350);
        cubic.setStartY(350);
        cubic.setEndX(250);
        cubic.setEndY(580);
        cubic.setControlX1(30);
        cubic.setControlY1(10);
        cubic.setControlX2(70);
        cubic.setControlY2(150);

        root.getChildren().add(cubic);







        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
