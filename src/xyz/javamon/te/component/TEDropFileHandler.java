package xyz.javamon.te.component;

import xyz.javamon.te.file.TEFileVisitor;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TEDropFileHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }
        Transferable t = support.getTransferable();
        try {
            String[] regexes = Files.lines(new File("./extract.list").toPath())
                    .map(x -> String.format(".*(%s).*", x))
                    .toArray(String[]::new);
            TEFileVisitor visitor = new TEFileVisitor(regexes);
            List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
            for (File f : files) Files.walkFileTree(f.toPath(), visitor);
        }
        catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
