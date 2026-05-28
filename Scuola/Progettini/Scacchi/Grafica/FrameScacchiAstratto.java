package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Partite.PartitaAstratta;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class FrameScacchiAstratto extends JFrame implements MouseListener {

    protected static final int DIMENSIONE_SCACCHIERA = 8;
    protected static final int DIMENSIONE_CASELLA = 100;

    protected static final Color COLORE_CHIARO = new Color(240, 217, 181);
    protected static final Color COLORE_SCURO = new Color(181, 136, 99);
    protected static final Color COLORE_SELEZIONE = new Color(246, 246, 105);
    protected static final Color COLORE_MOSSA = new Color(120, 200, 120);
    protected static final Color COLORE_CATTURA = new Color(220, 80, 80);
    protected static final Color COLORE_ERRORE = new Color(220, 80, 80);
    protected static final Color COLORE_ARROCCO = new Color(120, 170, 230);
    protected static final Color COLORE_MOVIMENTO = new Color(189, 189, 79);

    protected final JLabel[][] labels;
    protected final PartitaAstratta partita;
    protected final JLabel stato;
    protected final JLabel info;

    protected int rigaSelezionata = -1;
    protected int colonnaSelezionata = -1;

    protected int rigaMovimentoPartenza = -1;
    protected int colonnaMovimentoPartenza = -1;
    protected int rigaMovimentoArrivo = -1;
    protected int colonnaMovimentoArrivo = -1;
    protected boolean mostraUltimoMovimento = true;

    protected boolean erroreVisibile = false;
    protected boolean finito = false;
    protected Timer timerErrore;
    protected boolean conBot;

    protected FrameScacchiAstratto(String titolo, PartitaAstratta partita, boolean conBot) {
        super(titolo);

        this.partita = partita;
        this.labels = new JLabel[DIMENSIONE_SCACCHIERA][DIMENSIONE_SCACCHIERA];
        this.stato = new JLabel("", SwingConstants.CENTER);
        this.info = new JLabel("", SwingConstants.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);

        creaPannelloAlto();
        creaScacchiera();
        this.conBot = conBot;
    }

    public void avviaFrame() {
        aggiornaGrafica();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void creaPannelloAlto() {
        stato.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        stato.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel pannelloAlto = new JPanel(new GridLayout(2, 1));
        pannelloAlto.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        info.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        pannelloAlto.add(stato);
        pannelloAlto.add(info);
        add(pannelloAlto, BorderLayout.NORTH);
    }

    private void creaScacchiera() {
        JPanel scacchiera = new JPanel(new GridLayout(DIMENSIONE_SCACCHIERA, DIMENSIONE_SCACCHIERA));
        scacchiera.setPreferredSize(new Dimension(
                DIMENSIONE_CASELLA * DIMENSIONE_SCACCHIERA,
                DIMENSIONE_CASELLA * DIMENSIONE_SCACCHIERA
        ));

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
                label.setOpaque(true);
                label.putClientProperty("riga", riga);
                label.putClientProperty("colonna", colonna);
                label.addMouseListener(this);

                labels[riga][colonna] = label;
                scacchiera.add(label);
            }
        }

        add(scacchiera, BorderLayout.CENTER);
    }

    protected void aggiornaGrafica() {
        Casella[][] mappa = partita.getMappa();

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                JLabel label = labels[riga][colonna];
                label.setBackground(coloreBase(riga, colonna));
                label.setBorder(null);

                Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();
                label.setText(pezzo == null ? "" : simboloPezzo(pezzo.getNome()));
            }
        }

        evidenziaUltimoMovimento();

        if (rigaSelezionata != -1 && colonnaSelezionata != -1 && !finito) {
            labels[rigaSelezionata][colonnaSelezionata].setBackground(COLORE_SELEZIONE);
            evidenziaMossePossibili(rigaSelezionata, colonnaSelezionata);
        }

        aggiornaInfoExtra();

        if (!erroreVisibile && !finito) {
            stato.setForeground(Color.BLACK);
            stato.setText("Turno: " + nomeColore(partita.isAttaccaBianco()));
        }
    }

    private void evidenziaUltimoMovimento() {
        if (!mostraUltimoMovimento) return;

        if (coordinateMovimentoValide(rigaMovimentoPartenza, colonnaMovimentoPartenza)) {
            labels[rigaMovimentoPartenza][colonnaMovimentoPartenza].setBackground(COLORE_MOVIMENTO);
            labels[rigaMovimentoPartenza][colonnaMovimentoPartenza].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        if (coordinateMovimentoValide(rigaMovimentoArrivo, colonnaMovimentoArrivo)) {
            labels[rigaMovimentoArrivo][colonnaMovimentoArrivo].setBackground(COLORE_MOVIMENTO);
            labels[rigaMovimentoArrivo][colonnaMovimentoArrivo].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    private boolean coordinateMovimentoValide(int riga, int colonna) {
        return riga >= 0 && riga < DIMENSIONE_SCACCHIERA
                && colonna >= 0 && colonna < DIMENSIONE_SCACCHIERA;
    }

    protected void salvaUltimoMovimento(int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        rigaMovimentoPartenza = rigaPartenza;
        colonnaMovimentoPartenza = colonnaPartenza;
        rigaMovimentoArrivo = rigaArrivo;
        colonnaMovimentoArrivo = colonnaArrivo;
        mostraUltimoMovimento = true;
    }

    private void evidenziaMossePossibili(int riga, int colonna) {
        Pezzo pezzo = partita.getMappa()[riga][colonna].getPezzoContenuto();
        if (pezzo == null) return;

        Casella[][] mosse = pezzo.mossePossibili();
        Casella[][] mappa = partita.getMappa();

        for (int i = 0; i < DIMENSIONE_SCACCHIERA; i++) {
            for (int j = 0; j < DIMENSIONE_SCACCHIERA; j++) {
                boolean enPassant = partita.isEnPassantValido(pezzo, riga, colonna, i, j);

                if (mosse[i][j] == null && !enPassant) continue;
                if (partita.lasciaReSottoScacco(pezzo, riga, colonna, i, j)) continue;

                Pezzo pezzoDestinazione = mappa[i][j].getPezzoContenuto();

                if (enPassant || (pezzoDestinazione != null && pezzoDestinazione.isBianco() != pezzo.isBianco())) {
                    labels[i][j].setBackground(COLORE_CATTURA);
                    labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                } else {
                    labels[i][j].setBackground(COLORE_MOSSA);
                    labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
            }
        }
        evidenziaArroccoSePossibile(pezzo);
    }

    private void evidenziaArroccoSePossibile(Pezzo pezzo) {
        if (Character.toUpperCase(pezzo.getNome()) != 'R') return;

        int rigaRe = pezzo.isBianco() ? 7 : 0;
        if (rigaSelezionata != rigaRe || colonnaSelezionata != 4) return;

        if (puoArroccare(false)) {
            labels[rigaRe][6].setBackground(COLORE_ARROCCO);
            labels[rigaRe][6].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        }

        if (puoArroccare(true)) {
            labels[rigaRe][2].setBackground(COLORE_ARROCCO);
            labels[rigaRe][2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        }
    }

    public boolean puoArroccare(boolean latoLungo) {
        try {
            return partita.puoArroccare(latoLungo);
        } catch (RuntimeException ex) {
            return false;
        }
    }

    private Color coloreBase(int riga, int colonna) {
        return (riga + colonna) % 2 == 0 ? COLORE_CHIARO : COLORE_SCURO;
    }

    private String simboloPezzo(char nome) {
        switch (nome) {
            case 'R': return "♔";
            case 'D': return "♕";
            case 'T': return "♖";
            case 'A': return "♗";
            case 'C': return "♘";
            case 'P': return "♙";
            case 'r': return "♚";
            case 'd': return "♛";
            case 't': return "♜";
            case 'a': return "♝";
            case 'c': return "♞";
            case 'p': return "♟";
            default: return String.valueOf(nome);
        }
    }

    private void selezionaCasella(int riga, int colonna) {
        if (finito) return;

        Pezzo pezzo = partita.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzo == null) {
            deseleziona();
            return;
        }

        if (conBot && !partita.isAttaccaBianco()) {
            mostraErrore("Aspetta la mossa del bot");
            deseleziona();
            return;
        }

        if (conBot && !pezzo.isBianco()) {
            mostraErrore("Tu giochi con il Bianco");
            deseleziona();
            return;
        }

        if (pezzo.isBianco() != partita.isAttaccaBianco()) {
            mostraErrore("Non è il turno di questo colore");
            deseleziona();
            return;
        }

        rigaSelezionata = riga;
        colonnaSelezionata = colonna;
        aggiornaGrafica();
    }

    private void muoviSuCasella(int rigaArrivo, int colonnaArrivo) {
        if (finito) return;

        try {
            boolean coloreCheHaMosso = partita.isAttaccaBianco();
            Pezzo pezzo = partita.getMappa()[rigaSelezionata][colonnaSelezionata].getPezzoContenuto();

            if (isTentativoArrocco(pezzo, rigaArrivo, colonnaArrivo)) {
                boolean latoLungo = colonnaArrivo == 2;

                if (!puoArroccare(latoLungo)) {
                    throw new MossaNonValidaException("Arrocco non valido");
                }

                partita.arrocca(latoLungo);
                salvaUltimoMovimento(rigaSelezionata, colonnaSelezionata, rigaArrivo, colonnaArrivo);
            } else {
                partita.muoviPezzo(rigaSelezionata, colonnaSelezionata, rigaArrivo, colonnaArrivo);
                dopoMossaNormale(pezzo);
                salvaUltimoMovimento(rigaSelezionata, colonnaSelezionata, rigaArrivo, colonnaArrivo);
            }

            deseleziona();
            controllaFine(coloreCheHaMosso);
            muoviBotSeServe();

        } catch (MossaNonValidaException | IllegalArgumentException e) {
            mostraErrore(e.getMessage());
            deseleziona();
        }
    }

    private boolean isTentativoArrocco(Pezzo pezzo, int rigaArrivo, int colonnaArrivo) {
        if (pezzo == null) return false;
        if (Character.toUpperCase(pezzo.getNome()) != 'R') return false;

        return rigaArrivo == rigaSelezionata
                && colonnaSelezionata == 4
                && (colonnaArrivo == 2 || colonnaArrivo == 6);
    }

    private void muoviBotSeServe() {
        if (!conBot || finito || partita.isAttaccaBianco()) {
            return;
        }

        Timer timerBot = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    char[][] mappaPrima = copiaNomiPezzi();

                    partita.muoviPezzoConBot(false);
                    salvaUltimoMovimentoBot(mappaPrima, false);

                    aggiornaGrafica();
                    controllaFine(false);
                } catch (RuntimeException ex) {
                    mostraErrore("Errore bot: " + ex.getMessage());
                }
            }
        });

        timerBot.setRepeats(false);
        timerBot.start();
    }

    private char[][] copiaNomiPezzi() {
        char[][] copia = new char[DIMENSIONE_SCACCHIERA][DIMENSIONE_SCACCHIERA];
        Casella[][] mappa = partita.getMappa();

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();
                copia[riga][colonna] = pezzo == null ? '.' : pezzo.getNome();
            }
        }

        return copia;
    }

    private void salvaUltimoMovimentoBot(char[][] mappaPrima, boolean botBianco) {
        if (salvaArroccoBotSePresente(mappaPrima, botBianco)) {
            return;
        }

        Casella[][] mappaDopo = partita.getMappa();
        int rigaPartenza = -1;
        int colonnaPartenza = -1;
        int rigaArrivo = -1;
        int colonnaArrivo = -1;

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                char prima = mappaPrima[riga][colonna];
                Pezzo pezzoDopo = mappaDopo[riga][colonna].getPezzoContenuto();
                char dopo = pezzoDopo == null ? '.' : pezzoDopo.getNome();

                if (prima != '.' && isColorePezzo(prima, botBianco) && prima != dopo) {
                    rigaPartenza = riga;
                    colonnaPartenza = colonna;
                }

                if (dopo != '.' && isColorePezzo(dopo, botBianco) && prima != dopo) {
                    rigaArrivo = riga;
                    colonnaArrivo = colonna;
                }
            }
        }

        if (coordinateMovimentoValide(rigaPartenza, colonnaPartenza)
                && coordinateMovimentoValide(rigaArrivo, colonnaArrivo)) {
            salvaUltimoMovimento(rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo);
        }
    }

    private boolean salvaArroccoBotSePresente(char[][] mappaPrima, boolean botBianco) {
        int rigaRe = botBianco ? 7 : 0;
        char re = botBianco ? 'R' : 'r';
        char torre = botBianco ? 'T' : 't';
        Casella[][] mappaDopo = partita.getMappa();

        if (mappaPrima[rigaRe][4] != re) {
            return false;
        }

        if (mappaPrima[rigaRe][7] == torre
                && pezzoInCasella(mappaDopo, rigaRe, 6, re)
                && pezzoInCasella(mappaDopo, rigaRe, 5, torre)) {
            salvaUltimoMovimento(rigaRe, 4, rigaRe, 6);
            return true;
        }

        if (mappaPrima[rigaRe][0] == torre
                && pezzoInCasella(mappaDopo, rigaRe, 2, re)
                && pezzoInCasella(mappaDopo, rigaRe, 3, torre)) {
            salvaUltimoMovimento(rigaRe, 4, rigaRe, 2);
            return true;
        }

        return false;
    }

    private boolean pezzoInCasella(Casella[][] mappa, int riga, int colonna, char nome) {
        Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();
        return pezzo != null && pezzo.getNome() == nome;
    }

    private boolean isColorePezzo(char nome, boolean bianco) {
        return bianco ? Character.isUpperCase(nome) : Character.isLowerCase(nome);
    }

    protected abstract void aggiornaInfoExtra();

    private void dopoMossaNormale(Pezzo pezzoMosso) {
        if (pezzoMosso instanceof Pedone p) {
            Pezzo pezzo = partita.promuoviPedone(p);
            if (pezzo == null) return;
            partita.getMappa()[p.getRiga()][p.getColonna()].inserisciPezzo(pezzo);
        }
    }

    protected abstract void controllaFine(boolean coloreCheHaMosso);

    protected void finisciConMessaggio(String messaggio) {
        finito = true;
        rigaSelezionata = -1;
        colonnaSelezionata = -1;
        erroreVisibile = false;

        if (timerErrore != null && timerErrore.isRunning()) {
            timerErrore.stop();
        }

        aggiornaGrafica();
        stato.setForeground(Color.BLACK);
        stato.setText(messaggio);
        bloccaScacchiera();
    }

    private void deseleziona() {
        rigaSelezionata = -1;
        colonnaSelezionata = -1;
        aggiornaGrafica();
    }

    protected void mostraErrore(String messaggio) {
        if (finito) return;

        if (timerErrore != null && timerErrore.isRunning()) {
            timerErrore.stop();
        }

        erroreVisibile = true;

        stato.setText(messaggio != null && !messaggio.isBlank() ? messaggio : "Mossa invalida");
        stato.setForeground(COLORE_ERRORE);

        timerErrore = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroreVisibile = false;

                if (!finito) {
                    stato.setForeground(Color.BLACK);
                    stato.setText("Turno: " + nomeColore(partita.isAttaccaBianco()));
                }
            }
        });

        timerErrore.setRepeats(false);
        timerErrore.start();
    }

    protected boolean inputAbilitato() {
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (finito) return;

        if (!inputAbilitato()) {
            mostraErrore("Torna all'ultima mossa per continuare a giocare");
            return;
        }

        JLabel label = (JLabel) e.getSource();
        int riga = (int) label.getClientProperty("riga");
        int colonna = (int) label.getClientProperty("colonna");

        if (rigaSelezionata == -1 || colonnaSelezionata == -1) {
            selezionaCasella(riga, colonna);
            return;
        }

        if (riga == rigaSelezionata && colonna == colonnaSelezionata) {
            deseleziona();
            return;
        }

        Pezzo pezzoCliccato = partita.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzoCliccato != null && pezzoCliccato.isBianco() == partita.isAttaccaBianco()) {
            selezionaCasella(riga, colonna);
            return;
        }

        muoviSuCasella(riga, colonna);
    }

    private void bloccaScacchiera() {
        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                labels[riga][colonna].removeMouseListener(this);
                labels[riga][colonna].setEnabled(true);
            }
        }
    }

    protected void riattivaScacchiera() {
        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                labels[riga][colonna].setEnabled(true);

                boolean listenerPresente = false;
                MouseListener[] listeners = labels[riga][colonna].getMouseListeners();
                for (MouseListener listener : listeners) {
                    if (listener == this) {
                        listenerPresente = true;
                        break;
                    }
                }

                if (!listenerPresente) {
                    labels[riga][colonna].addMouseListener(this);
                }
            }
        }
    }

    protected String nomeColore(boolean bianco) {
        return bianco ? "Bianco" : "Nero";
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (finito) return;

        JLabel label = (JLabel) e.getSource();
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (finito) return;
        aggiornaGrafica();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    public PartitaAstratta getPartita() {
        return partita;
    }
}