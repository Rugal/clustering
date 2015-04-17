package ga.rugal.clustering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class MainFrame extends javax.swing.JFrame
{

    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        drawPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        clusterButton = new javax.swing.JMenuItem();
        cleanButton = new javax.swing.JMenuItem();
        radiusButton = new javax.swing.JMenuItem();
        authorMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        drawPanel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                drawPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 822, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        jMenu1.setText("Cluster");
        jMenu1.setToolTipText("Do clustering action");

        clusterButton.setText("cluster");
        clusterButton.setToolTipText("Do clustering action");
        clusterButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                clusterButtonActionPerformed(evt);
            }
        });
        jMenu1.add(clusterButton);

        cleanButton.setText("clean");
        cleanButton.setToolTipText("Clean all points in panel");
        cleanButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cleanButtonActionPerformed(evt);
            }
        });
        jMenu1.add(cleanButton);

        radiusButton.setText("radius");
        radiusButton.setToolTipText("Set radius for each node");
        radiusButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radiusButtonActionPerformed(evt);
            }
        });
        jMenu1.add(radiusButton);

        jMenuBar1.add(jMenu1);

        authorMenu.setText("Author");
        jMenuBar1.add(authorMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cleanButtonActionPerformed
    {//GEN-HEADEREND:event_cleanButtonActionPerformed
        clean();
        drawPanel.repaint();
        points.clear();

    }//GEN-LAST:event_cleanButtonActionPerformed

    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_drawPanelMouseClicked
    {//GEN-HEADEREND:event_drawPanelMouseClicked
        Circle c = new Circle(evt.getX(), evt.getY());
        points.add(c);
        clusterButtonActionPerformed(null);
        draw();

    }//GEN-LAST:event_drawPanelMouseClicked

    private void radiusButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radiusButtonActionPerformed
    {//GEN-HEADEREND:event_radiusButtonActionPerformed
        try
        {
            this.setRadius(Integer.parseInt(javax.swing.JOptionPane.showInputDialog(this, "Fill in new diameter", 10)));
            cleanButtonActionPerformed(null);
        }
        catch (NumberFormatException e)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid diameter (from " + MIN_DIAMETER + " to " + MAX_DIAMETER + ")");
        }

    }//GEN-LAST:event_radiusButtonActionPerformed

    private void clusterButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clusterButtonActionPerformed
    {//GEN-HEADEREND:event_clusterButtonActionPerformed
        clean();
        System.out.println("Clustering Start:");
        clustering();
        print();
    }//GEN-LAST:event_clusterButtonActionPerformed

    private void draw()
    {
        points.stream().forEach((point) ->
        {
            Graphics2D g2d = (Graphics2D) (drawPanel.getGraphics());
            drawPanel.getGraphics().drawRect(point.X, point.Y, 1, 1);

            g2d.setColor(colors[point.getBelongTo()]);
            g2d.fillOval(point.X - radius, point.Y - radius, 2 * radius, 2 * radius);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }

    /**
     * 1. Treat first node as part of cluster 1
     *
     * 2. Go through all nodes to exam if they belong to this cluster
     * 2.1. If true, add them to candidate list so to spread
     * 2.2. If false, wait until next turn
     *
     * 3. After exam all nodes from the first node, repeat step 2 from candidate
     * list to spread to whole pattern
     *
     * 4. After exhaust all candidate, exam next node that belongs to no
     * cluster and mark it to cluster 2, do it from step 1
     *
     * 5. After exhaust all nodes, clustering end
     *
     *
     * @param nodes
     */
    private void clustering()
    {
        LinkedList<Circle> bfsQueue = new LinkedList<>();

        Circle free;
        while ((free = getFirstFreeNode()) != null)
        {
            free.setBelongTo(currentClusterNumber++);
            bfsQueue.add(free);
            while (!bfsQueue.isEmpty())
            {
                Circle current = bfsQueue.removeFirst();
                bfsQueue.addAll(spread(current));
            }
        }

    }

    private Circle getFirstFreeNode()
    {
        for (Circle point : points)
        {
            if (point.isFree())
            {
                return point;
            }
        }
        return null;
    }

    private List<Circle> spread(Circle current)
    {
        List<Circle> spreadList = new ArrayList<>();

        points.stream().filter((point) -> (!current.inCluster(point) && !current.equals(point) && current.isNearby(point, radius))).map((point) ->
        {
            point.setBelongTo(current.getBelongTo());
            return point;
        }).forEach(spreadList::add);

        return spreadList;
    }

    private void clean()
    {
        currentClusterNumber = 1;
        points.stream().forEach((point) ->
        {
            point.setBelongTo(0);
        });
        Collections.sort(points);
    }

    private void print()
    {
        Set<Integer> clusterInAll = new HashSet<>();
        points.stream().forEach((point) ->
        {
            clusterInAll.add(point.getBelongTo());
            System.out.println(point);
        });
        System.out.println("There are " + clusterInAll.size() + " clusters:");
        clusterInAll.stream().forEach((c) ->
        {
            System.out.print(c);
            System.out.print(" ");
        });
        System.out.println("\nClustering Complete\n");

    }

    /**
     * Get the value of radius
     *
     * @return the value of radius
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * Set the value of radius
     *
     * @param radius new value of radius
     */
    public void setRadius(int radius)
    {
        if (radius <= MAX_DIAMETER && radius >= MIN_DIAMETER)
        {
            this.radius = radius;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu authorMenu;
    private javax.swing.JMenuItem cleanButton;
    private javax.swing.JMenuItem clusterButton;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem radiusButton;
    // End of variables declaration//GEN-END:variables

    private int currentClusterNumber = 1;

    private final List<Circle> points = new ArrayList<>();

    public static final int MAX_DIAMETER = 100;

    public static final int MIN_DIAMETER = 10;

    private static final Color[] colors = new Color[]
    {
        Color.BLACK, Color.GREEN, Color.RED, Color.WHITE, Color.BLUE, Color.PINK, Color.ORANGE, Color.MAGENTA
    };

    private int radius = 30;

}
