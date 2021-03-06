/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peduzzi_samuele
 */
public class JDatiCondivisi {

    private Vector buffer;
    private Semaphore synchVisCarattereGenerato;
    private Semaphore synchVisVisualizzato;

    private Semaphore synchCountPuntiCarattereGenerato;
    private Semaphore synchCountPuntiCarattereLetto;

    private Semaphore synchCountSpaziiCarattereGenerato;
    private Semaphore synchCountSpaziiCarattereLetto;

    private int numSpaziInseriti, numPuntiInseriti;
    private int numSpaziLetti, numPuntiLetti;

    public JDatiCondivisi() {
        buffer = new Vector(1, 1);

        synchVisCarattereGenerato = new Semaphore(0);
        synchVisVisualizzato = new Semaphore(1);

        synchCountPuntiCarattereGenerato = new Semaphore(0);
        synchCountPuntiCarattereLetto = new Semaphore(1);

        synchCountSpaziiCarattereGenerato = new Semaphore(0);
        synchCountSpaziiCarattereLetto = new Semaphore(1);

        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
    }

    //Metodi per la gestione del buffer 
    public synchronized char getLastElement() {
        return (char) buffer.lastElement();
    }

    public synchronized void pushChar(char carattere) {
        buffer.add(carattere);
    }

    public synchronized int getBufferLenght() {
        return buffer.size();
    }

    public char getElementAt(int index) {
        return (char) buffer.elementAt(index);
    }

    public String bufferToString() {
        return buffer.toString();
    }

    //Metodi per la gestione del numero di spazii e punti
    public synchronized void incnumSpaziInseriti() {
        numSpaziInseriti++;
    }

    public synchronized void incnumPuntiInseriti() {
        numPuntiInseriti++;
    }

    public synchronized void incnumSpaziLetti() {
        numSpaziLetti++;
    }

    public synchronized void incnumPuntiLetti() {
        numPuntiLetti++;
    }

    public int getNumSpaziInseriti() {
        return numSpaziInseriti;
    }

    public int getNumPuntiInseriti() {
        return numPuntiInseriti;
    }

    public int getNumSpaziLetti() {
        return numSpaziLetti;
    }

    public int getNumPuntiLetti() {
        return numPuntiLetti;
    }

    //Metodi per la sincronizzazione dei thread
    public void attendiVisCarattereGenerato() {

        synchVisCarattereGenerato.acquireUninterruptibly();

    }

    public void segnalaVisCarattereGenerato() {
        synchVisCarattereGenerato.release();
    }

    public void attendiVisVisualizzato() {
        try {
            synchVisVisualizzato.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JDatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void segnalaVisVisualizzato() {
        synchVisVisualizzato.release();
    }

    public void attendiCountPuntiCarattereGenerato() {
        try {
            synchCountPuntiCarattereGenerato.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JDatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void segnalaCountPuntiCarattereGenerato() {
        synchCountPuntiCarattereGenerato.release();
    }

    public void attendiCountPuntiCarattereLetto() {
        try {
            synchCountPuntiCarattereLetto.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JDatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void segnalaCountPuntiCarattereLetto() {
        synchCountPuntiCarattereLetto.release();
    }

    public void attendiCountSpaziiCarattereGenerato() {
        try {
            synchCountSpaziiCarattereGenerato.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JDatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void segnalaCountSpaziiCarattereGenerato() {
        synchCountSpaziiCarattereGenerato.release();
    }

    public void attendiCountSpaziiCarattereLetto() {
        try {
            synchCountSpaziiCarattereLetto.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JDatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void segnalaCountSpaziiCarattereLetto() {
        synchCountSpaziiCarattereLetto.release();
    }

}
