package com.cqut.atao.controller;

import com.cqut.atao.lexical.Lexer;
import com.cqut.atao.syntax.TokenList;
import com.cqut.atao.syntax.strategy.statement.Syntax;
import com.cqut.atao.syntax.tree.MyTree;
import com.cqut.atao.token.Token;
import com.cqut.atao.util.TokenUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private GridPane rootLayout;

    @FXML
    private TextArea coderArea;

    @FXML
    private TextArea tokenArea;

    @FXML
    private TextArea treeArea;

    private Lexer lexer = new Lexer();

    private Syntax syntax = new Syntax();

    private List<Token> tokens;

    @FXML
    public void open(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        String text = "";
        String line = null;
        while ((line = reader.readLine()) != null){
            text += line+"\n";
        }
        coderArea.setText(text);
    }

    @FXML
    public void lexer(ActionEvent actionEvent) throws IOException {
        // 获取代码
        String coder = coderArea.getText();
        // 解析token
        lexer.lexicalAnalysis(coder);
        // 获取token
        tokens = lexer.getTokens();
        // 回显
        String res = TokenUtil.displayToken(tokens);
        tokenArea.setText(res);
        refresh();
    }

    @FXML
    public void syntax(ActionEvent actionEvent) throws IOException {
        // 构造语法树
        TokenList<Token> tokenList = new TokenList<>(tokens);
        MyTree tree = new MyTree();
        List<Exception> exceptions = new ArrayList<>();
        // 生成语法树
        syntax.Program(tree, tokenList, exceptions);
        // 回显
       if (exceptions.size() != 0){
           treeArea.setText(TokenUtil.displayException(exceptions));
       }else {
           treeArea.setText(tree.toString());
       }
    }

    private void refresh(){
        treeArea.setText("");
    }

}