package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Product;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public static final String FILE_PRODUCT = "src\\model\\storage\\product.txt";
    private final ObservableList<Integer> listChoiceSize = FXCollections.observableArrayList(37, 38, 39, 40, 41, 42, 43);
    private final ObservableList<String> listChoiceBrand = FXCollections.observableArrayList("Adidas", "Nike", "Fila", "Newbalance");
    private ObservableList<Product> productList;

//    @FXML
//    private Button btnUpdate;
//
//    @FXML
//    private Button btnDelete;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField searchText;


    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField colorText;

    @FXML
    private ComboBox<Integer> comboBoxSize;

    @FXML
    private ComboBox<String> comboBoxBrand;

    @FXML
    private TextField priceText;

    @FXML
    private TextField imageText;

    @FXML
    private ImageView imagePreView;

    @FXML
    private TextArea desText;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> colorColumn;

    @FXML
    private TableColumn<Product, String> sizeColumn;

    @FXML
    private TableColumn<Product, String> brandColumn;

    @FXML
    private TableColumn<Product, String> priceColumn;

    @FXML
    private TableColumn<Product, String> imageColumn;

    @FXML
    private TableColumn<Product, String> desColumn;


    public void logout() throws IOException {
        writeFile();
        //thoat ve login
        btnLogout.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene scene = new Scene(root, 667, 492);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("WELCOME TO MANAGEMENT LOGIN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public void imageButton(MouseEvent mouseEvent) throws MalformedURLException {
//        FileChooser fileChooser = new FileChooser();
//        File fileSelected = fileChooser.showOpenDialog(null);
//        String imageFile = fileSelected.toURI().toURL().toString();
//        Image image = new Image(imageFile);
//        imagePreView.setImage(image);
//        imageText.setText(fileSelected.toURI().toURL().toString());
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxSize.setItems(listChoiceSize);
        comboBoxBrand.setItems(listChoiceBrand);

        productList = FXCollections.observableArrayList(readFile());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        table.getItems().addAll(productList);
        table.setItems(productList);
    }

    public void addProduct(ActionEvent actionEvent) {
        Product newProduct = new Product();
        newProduct.setId(idText.getText());
        newProduct.setName(nameText.getText());
        newProduct.setColor(colorText.getText());
        newProduct.setSize(comboBoxSize.getValue());
        newProduct.setBrand(comboBoxBrand.getValue());
        newProduct.setPrice(priceText.getText());
        newProduct.setImage(imageText.getText());
        newProduct.setDescription(desText.getText());
        productList.add(newProduct);
        writeFile();
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selected = table.getSelectionModel().getSelectedItem();
        productList.remove(selected);
        writeFile();
    }

    public void writeFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PRODUCT));
            for (Product product : productList) {
                outputStream.writeObject(product);
            }
            outputStream.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NotSerializableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> readFile() {
        List<Product> productList = new ArrayList<>();
        try {
            ObjectInputStream readFile = new ObjectInputStream(new FileInputStream(FILE_PRODUCT));
            while (true) {
                productList.add((Product) readFile.readObject());
            }
        } catch (EOFException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productList;
    }


}
