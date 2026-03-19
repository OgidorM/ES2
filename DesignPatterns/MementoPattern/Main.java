import com.es2.memento.BackupService;
import com.es2.memento.ExistingStudentException;
import com.es2.memento.NotExistingSnapshotException;
import com.es2.memento.Server;


public class Main {
    public static void main(String[] args) throws ExistingStudentException, NotExistingSnapshotException {
        Server s = new Server();
        BackupService backup = new BackupService(s);

        backup.takeSnapshot();               // Snapshot 0: []
        s.addStudent("Maria Maria");

        backup.takeSnapshot();               // Snapshot 1: ["Maria Maria"]
        s.addStudent("João João");

        System.out.println(s.getStudentNames().size()); // Esperado: 2

        backup.restoreSnapshot(1);           // Restaura Snapshot 1: ["Maria Maria"]

        System.out.println(s.getStudentNames().size()); // Esperado: 1
    }
}