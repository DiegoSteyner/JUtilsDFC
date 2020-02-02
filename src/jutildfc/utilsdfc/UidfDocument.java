package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.common.IDfId;

public class UidfDocument
{
    private UidfDocument()
    {

    }

    public static IDfDocument getIdfDocument(UidfSession s, Object objid) throws Exception
    {
        return ((IDfDocument) s.getIdfSysObject(objid));
    }

    public static IDfId createNewObjectInSameFolder(IDfDocument document) throws Exception
    {
        return (document.saveAsNew(Boolean.FALSE));
    }

    public static IDfACL getAclDocument(UidfSession s, Object document) throws Exception
    {
        if (document instanceof IDfDocument)
        {
            return (((IDfDocument) document).getACL());
        }
        else
        {
            return (s.getIdfSysObject(document).getACL());
        }
    }

    public static IDfACL getAclFolderTheDocument(UidfSession s, Object document) throws Exception
    {
        if (document instanceof IDfDocument)
        {
            return (((IDfFolder) s.getIdfPersistentObject(((IDfDocument) document).getFolderId(0))).getACL());
        }
        else
        {
            return (((IDfFolder) s.getIdfPersistentObject(((IDfDocument) s.getIdfSysObject(document)).getFolderId(0))).getACL());
        }
    }
}
