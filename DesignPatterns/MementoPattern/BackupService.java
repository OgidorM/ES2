package com.es2.memento;

import java.util.ArrayList;
// Caretaker

 // 1) Solicitar snapshots ao Originator (backup.takeSnapshot() → server.backup())
 // 2) Armazenar os Mementos numa estrutura de dados (ArrayList<Memento>)
 // 3) Solicitar restauração ao Originator (backup.restoreSnapshot(n) → server.restore(memento))

public class BackupService {

    private final Server server;

    private final ArrayList<Memento> snapshots;

    public BackupService(Server server) {
        this.server = server;
        this.snapshots = new ArrayList<>();
    }

    // Tirar snapshot (backup) antes de cada ação
    public void takeSnapshot() {
        Memento memento = server.backup();
        snapshots.add(memento);
    }


    public void restoreSnapshot(int snapshotNumber) throws NotExistingSnapshotException {
        if (snapshotNumber < 0 || snapshotNumber >= snapshots.size()) {
            throw new NotExistingSnapshotException();
        }
        Memento memento = snapshots.get(snapshotNumber);
        server.restore(memento);
    }
}

