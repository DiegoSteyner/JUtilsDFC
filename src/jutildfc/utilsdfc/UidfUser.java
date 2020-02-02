package jutildfc.utilsdfc;

import java.util.ArrayList;
import java.util.List;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.client.IDfWorkitem;

import jutildfc.data.VarsDfc;
import jutildfc.interfaces.AbstractUtilDfc;

public class UidfUser extends AbstractUtilDfc
{

    private UidfUser()
    {
        super(null);
    }

    /**
     * Metodo que lista todas as tarefas disponiveis na inbox do usuario
     * 
     * @param usuario O nome do usuario
     * 
     * @return Uma Lista de Workitens presentes na inbox do usuario
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static List<IDfWorkitem> getTasksInInboxUser(UidfSession s, String userName) throws Exception
    {
        List<IDfWorkitem> retorno = new ArrayList<IDfWorkitem>();
        IDfCollection tasks = s.getTasksForUser(userName, "task_name");

        while (tasks.next())
        {
            retorno.add((IDfWorkitem) s.getIdfPersistentObject(tasks.getId("item_id")));
        }
        UIdfCollections.closeCollection(tasks);

        return (retorno);
    }

    /**
     * Metodo que retorna um usuario
     * 
     * @param userName O nome do usuario
     * 
     * @return O objeto IDfUser do usuario
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public IDfUser getUser(String userName) throws Exception
    {
        return (getIdfSession().getUser(userName));
    }

    /**
     * Metodo que exibe as informacoes do usuario
     * 
     * @param userName O nome do usuario
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public void displayUser(String userName) throws Exception
    {
        IDfUser user = getUser(userName);

        String[] campos = new String[13];
        String[] valore = new String[13];

        print("INFORMAÇÕES SOBRE O USUÁRIO \n\n");

        campos[0] = "Nome do Usuario";
        campos[1] = "Login do usuario";
        campos[2] = "Grupo Usuario";
        campos[3] = "Permissão de Owner";
        campos[4] = "Permissão de Grupo";
        campos[5] = "Permissão World";
        campos[6] = "Client Capabilities";
        campos[7] = "Email";
        campos[8] = "Privilégios";
        campos[9] = "Estado do usuario";
        campos[10] = "Usuario Pode receber Workflows";
        campos[11] = "Auto Delegar";
        campos[12] = "Pasta Home";

        valore[0] = user.getUserName();
        valore[1] = user.getUserLoginName();
        valore[2] = user.getUserGroupName();
        valore[3] = VarsDfc.getPermission(user.getOwnerDefPermit());
        valore[4] = VarsDfc.getPermission(user.getGroupDefPermit());
        valore[5] = VarsDfc.getPermission(user.getWorldDefPermit());
        valore[6] = VarsDfc.getNameClientCapabilities(user.getClientCapability());
        valore[7] = user.getUserAddress();
        valore[8] = VarsDfc.getUserPrivileges(user.getUserPrivileges());
        valore[9] = VarsDfc.getUserState(user.getUserState());
        valore[10] = String.valueOf(!user.getValue("workflow_disabled").asBoolean());
        valore[11] = (user.getUserDelegation() != null && !user.getUserDelegation().isEmpty()) ? valore[9] : "Nenhum usuário configurado para auto delegate";
        valore[12] = user.getDefaultFolder();

        printLnDump(campos, valore, "      ");
        print("\nINFORMAÇÕES DA ACL DO USUÁRIO \n\n");

        IDfACL aclUser = getIdfSession().getACL(user.getACLDomain(), user.getACLName());

        String[] grupos = new String[aclUser.getAccessorCount()];
        String[] permis = new String[grupos.length];

        for (int i = 0; i < grupos.length; i++)
        {
            grupos[i] = aclUser.getAccessorName(i);
            permis[i] = VarsDfc.getPermission(aclUser.getAccessorPermit(i));
        }

        printLnDump(grupos, permis, "      ");
    }
}
