package com.lucianbc.onechat.client.view;

import com.lucianbc.onechat.data.UserIdentity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AppPane extends JPanel {

    private UserIdentity loggedUser = new UserIdentity("", "");
    private AbstractListModel<UserIdentity> connectedUsers;

    AppPane(AbstractListModel<UserIdentity> connectedUsers) {
        this.setBackground(Color.CYAN);
        this.connectedUsers = connectedUsers;
//        init(connectedUsers);
    }

    public void setLoggedUser(UserIdentity loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void init() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 100;
        c.weighty = 20;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;


        Panel p1 = new Panel();
        p1.add(new JLabel(loggedUser.getUsername()));  // this needs binding
        p1.add(new JLabel("Double click to chat!"));
        this.add(p1, c);

        Panel p2 = new Panel();

        JList<UserIdentity> connectedUsersList = new JList<>(this.connectedUsers);
        connectedUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectedUsersList.setVisibleRowCount(-1);
        connectedUsersList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jList, Object o, int i, boolean b, boolean b1) {
                super.getListCellRendererComponent(jList, o, i, b, b1);
                setText(((UserIdentity) o).getUsername());
                return this;
            }
        });
        connectedUsersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    System.out.println("Will start chat with " + connectedUsersList.getSelectedValue());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(connectedUsersList);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        p2.setLayout(new BorderLayout());
        p2.add(scrollPane, BorderLayout.CENTER);
        c.gridy = 1;
        c.weighty = 80;
        this.add(p2, c);
    }
}

