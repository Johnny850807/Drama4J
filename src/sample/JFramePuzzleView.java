/*
 * Copyright 2020 Waterball (johnny850807@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package sample;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class JFramePuzzleView extends JFrame implements PuzzleView {
    private JTextField description;
    private JTextField text;
    private JButton[] buttons = new JButton[9];
    private Map<Integer, List<Consumer<Integer>>> onNumberInputListeners = new LinkedHashMap<>();

    public JFramePuzzleView() {
        super("Puzzle");
    }

    @Override
    public void launch() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JPanel content = new JPanel();
        description = new JTextField("Welcome...", 20);
        description.setEditable(false);
        text = new JTextField(16);
        text.setEditable(false);
        content.add(description);
        content.add(text);

        for (int i = 1; i <= 9; i++) {
            final int number = i;
            buttons[i-1] = new JButton(String.valueOf(i));
            content.add(buttons[i-1]);
            buttons[i-1].addActionListener(e -> {
                for (Consumer<Integer> onNumberInputListener : onNumberInputListeners.get(number)) {
                    onNumberInputListener.accept(number);
                }
            });
        }

        content.setBackground(Color.blue);
        setSize(270, 200);
        setContentPane(content);
        setVisible(true);
    }

    @Override
    public void addOnNumberInputListener(int number, Consumer<Integer> numberConsumer) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("The range of number is within 1~9.");
        }
        onNumberInputListeners.putIfAbsent(number, new CopyOnWriteArrayList<>());
        onNumberInputListeners.get(number).add(numberConsumer);
    }

    @Override
    public void setDescription(String description) {
        this.description.setText(description);
    }

    public static void main(String[] args) {
        JFramePuzzleView phoneView = new JFramePuzzleView();
        phoneView.launch();
    }
}
