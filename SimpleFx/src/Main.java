import com.sun.javafx.geom.Vec2f;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.Random;


/**
 * Created by byte on 25.10.14.
 *
 */
public class Main extends Application {

    private int windowWidth = 600,
                windowHeight = 600;

    private Random r = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple fx application");
        primaryStage.setScene(createScene());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    //method create Scene
    private Scene createScene(){
        Group root = new Group();
        for (int i = 0; i < 15; i++) {
            root.getChildren().add(createLabel());
        }
        Scene scene = new Scene(root, windowWidth, windowHeight);
        return scene;
    }


    private Label createLabel(){
        Label label = LabelBuilder.create()
                .text("SomeText")
                .prefWidth(100)
                .prefHeight(50)
                .alignment(Pos.CENTER)
                .layoutX(r.nextInt(windowWidth)-100)
                .layoutY(r.nextInt(windowHeight)-50)
                .style("-fx-background-color: orange;")
                .build();
        addTranslateListener(label);
        return label;

    }
    private Vec2f delta;
    private Node node;
    private boolean is_rotate, is_catch;

    private void addTranslateListener(final Node node){
        node.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                 delta = new Vec2f((float)(event.getSceneX() - node.getLayoutX())
                                    ,(float)(event.getSceneY() - node.getLayoutY()));
                Main.this.node = node;
                if (event.getButton() == MouseButton.SECONDARY) is_rotate = true; else is_catch = true;


            }
        });

        node.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.this.node = null;
                if (event.getButton() == MouseButton.SECONDARY)
                is_rotate = false; else is_catch = false;
            }
        });

        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (node != null){
                    if (is_rotate){
                        double dx1 = event.getSceneX() - node.getLayoutX();
                        double dy1 = event.getSceneY() - node.getLayoutY();
                        double l = Math.sqrt(dx1 * dx1 + dy1 * dy1);

                        dx1 /= l;
                        dy1 /= l;
                        double angle = Math.PI/2;
                        double dx2 = Math.sin(angle);
                        double dy2 = Math.cos(angle);

                        double cosA = dx1 * dx2 + dy1 * dy2;

                        angle = Math.acos(cosA);

                        if (dy1 < 0) angle = Math.PI - angle;
                        node.setRotate(angle / Math.PI * 180);

                    }
                    if (is_catch){
                        node.setLayoutX(event.getSceneX() - delta.x);
                        node.setLayoutY(event.getSceneY() - delta.y);
                    }
                }
            }
        });

    }



}
