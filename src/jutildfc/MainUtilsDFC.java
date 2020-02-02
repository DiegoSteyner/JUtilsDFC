package jutildfc;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPackage;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfWorkitem;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfList;
import com.documentum.fc.common.IDfList;

import jutildfc.utilsdfc.UIdfQuery;
import jutildfc.utilsdfc.UidfSession;
import net.minidev.json.JSONObject;

@SuppressWarnings("unused")
public class MainUtilsDFC
{
    private static final String dfcFolderConf   = "<discoLocal>:\\Documentum\\config";
    private static final String docBaseName     = "<DOCBASE_NAME>";
    private static final String docBaseUserPass = "<USER_PASS>";
    private static final String docBaseUserName = "<USER_NAME>";

    /**
     * Classe Principal Nova
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        try
        {
            UidfSession session = new UidfSession(dfcFolderConf);

            if (session.connectToDocbaseWithCredentials(docBaseUserName, docBaseUserPass, docBaseName))
            {
                System.out.println("Conectado");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.println("Desconectado da docbase");
        }
    }
}
