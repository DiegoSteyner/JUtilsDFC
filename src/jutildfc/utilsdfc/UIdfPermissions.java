package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSysObject;

import jutildfc.data.VarsDfc;

public class UIdfPermissions
{
    private UidfSession session = null;

    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UidfSession} configurado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfPermissions(UidfSession session)
    {
        this.session = session;
    }

    /**
     * Método que remove um usuário e todas as suas respectivas
     * permissões de dentro da Acl
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param user O usuário que se deseja remover
     * 
     * @return Se True, O usuario foi removido com sucesso
     * @throws Exception
     */
    public boolean removeUserFromAcl(String objIdPath, String user) throws Exception
    {
        IDfACL acl = (IDfACL) getSession().getIdfPersistentObject(objIdPath);
        boolean retorno = false;

        if (acl == null)
        {
            return true;
        }

        for (int i = 0; i < acl.getAccessorCount(); i++)
        {
            if (acl.getAccessorName(i).equals(user))
            {
                acl.remove(VarsDfc.ATTR_ACCESSOR_NAME, i);
                acl.remove(VarsDfc.ATTR_ACCESSOR_PERMIT, i);
                acl.remove(VarsDfc.ATTR_ACCESSOR_XPERMIT, i);
                acl.remove(VarsDfc.ATTR_IS_GROUP, i);
                acl.save();
                retorno = true;
                break;
            }
        }
        return (retorno);
    }

    /**
     * Metodo que altera as permissoes basica de um Objeto
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param anotherUser O usuario a ser alterada as permissoes
     * @param permission O tipo da permissao {@link VarsDfc}
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void changeBasicPermissions(String objIdPath, String anotherUser, int permission) throws Exception
    {
        IDfSysObject sysObj = getSession().getIdfSysObject(objIdPath);
        sysObj.grant(anotherUser, permission, "");
        sysObj.save();
    }

    /**
     * Metodo que altera as permissoes Extendidas de um Objeto
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param anotherUser O usuario a ser alterada as permissoes
     * @param permission O tipo da permissao {@link VarsDfc}
     * @param extended A Permissao extendida a ser alterada {@link VarsDfc}
     * 
     * @param grantOrRevoke <blockquote>true, Grant()</blockquote>
     * 						<blockquote>false, Revoke()</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void changeExtendedPermissions(String objIdPath, String anotherUser, int permission, String extended, boolean grantOrRevoke) throws Exception
    {
        IDfSysObject sysObj = getSession().getIdfSysObject(objIdPath);

        if (grantOrRevoke)
        {
            sysObj.grant(anotherUser, permission, extended);
        }
        else
        {
            sysObj.revoke(anotherUser, extended);
        }
        sysObj.save();
    }

    /**
     * Metodo que altera as ACL's de um Objeto
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param permission O tipo da permissao {@link VarsDfc}
     * @param extPermission A Permissao extendida a ser alterada {@link VarsDfc}
     * @param aclName O nome da acl a ser Aplicada
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    @SuppressWarnings("deprecation")
    public void privateACLs(String objIdPath, String permission, String extPermission, String aclName) throws Exception
    {
        IDfSysObject sysObj = getSession().getIdfSysObject(objIdPath);
        IDfPersistentObject pObj = getSession().createNewObject(VarsDfc.OBJ_TYPE_DM_TYPE_ACL);

        pObj.apiSet("set", "object_name", aclName);
        pObj.apiExec("grant", "dm_owner," + permission + "," + extPermission);
        pObj.apiExec("grant", "dm_world," + permission + "," + extPermission);
        pObj.save();

        IDfACL oldACL = sysObj.getACL();
        IDfACL newACL = getSession().getSession().getACL(getSession().getCompleteUserName(), aclName);

        sysObj.setACL(newACL);
        sysObj.save();

        sysObj.setACL(oldACL);
        sysObj.save();
    }

    /**
     * Metodo que mostra as permissoes basica de um Objeto
     * 
     * @param sysObj O Objeto
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayBasicPermissions(IDfSysObject sysObj) throws Exception
    {
        System.out.println("ACL Name: ".concat(sysObj.getACLName()));
        String s = "";

        for (int i = 0; i < sysObj.getAccessorCount(); i++)
        {
            s = "\tAccessor: ".concat(sysObj.getAccessorName(i));
            while (s.length() < 35)
            {
                s = s.concat(" ");
            }
            System.out.println(s.concat("- Permission: ".concat(permissionToString(sysObj.getAccessorPermit(i)))));
        }
    }

    /**
     * Metodo que mostra as permissoes extendidas de um Objeto
     * 
     * @param sysObj O objeto
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayExtendedPermissions(IDfSysObject sysObj) throws Exception
    {
        System.out.println("ACL Name: ".concat(sysObj.getACLName()));
        String s = "";

        for (int i = 0; i < sysObj.getAccessorCount(); i++)
        {
            s = "\tAccessor: ".concat(sysObj.getAccessorName(i));
            while (s.length() < 35)
            {
                s = s.concat(" ");
            }
            System.out.println(s + " - Extended Permissions: " + sysObj.getAccessorXPermitNames(i)); // Ext
                                                                                                     // permissions
        }
    }

    /**
     * Metodo que retorna o nome da permissao basica
     * 
     * @param permission O numero da permissao (NUMERO)
     * 
     * @return O nome da permissao
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String permissionToString(String permission) throws Exception
    {
        return (VarsDfc.BASIC_PERMISSIONS.get(Integer.parseInt(permission)));
    }

    /**
     * Metodo que retorna o nome da permissao extendida
     * 
     * @param permission O numero da permissao (NUMERO)
     * 
     * @return O nome da permissao extendida
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String extendedPermissionToString(String permission) throws Exception
    {
        return (VarsDfc.EXTENDED_PERMISSIONS.get(Integer.parseInt(permission)));
    }

    public UidfSession getSession()
    {
        return session;
    }

    public void setSession(UidfSession session)
    {
        this.session = session;
    }
}
