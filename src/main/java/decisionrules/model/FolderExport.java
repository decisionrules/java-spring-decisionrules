package decisionrules.model;

import java.util.Date;
import java.util.List;

public class FolderExport {

    public Export export; // maps to the outer "export" field in JSON

    public FolderExport() {
    }

    public FolderExport(Export export) {
        this.export = export;
    }

    public static class Export {
        public ExportFolderData data;
        public String exportType;
        public Integer version;
        public Date createdAt;

        public Export() {
        }

        public Export(ExportFolderData data, String exportType, Integer version, Date createdAt) {
            this.data = data;
            this.exportType = exportType;
            this.version = version;
            this.createdAt = createdAt;
        }
    }

    public static class ExportFolderData {
        public FolderData structure;
        public List<Rule> rules;

        public ExportFolderData() {
        }

        public ExportFolderData(FolderData structure, List<Rule> rules) {
            this.structure = structure;
            this.rules = rules;
        }
    }
}