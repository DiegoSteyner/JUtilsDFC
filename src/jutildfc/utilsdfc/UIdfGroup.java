package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfGroup;
import com.documentum.fc.client.IDfUser;

public class UIdfGroup
{
    private UIdfGroup()
    {
    }

    public static boolean addUserInGroup(UidfSession s, IDfGroup g, IDfUser u) throws Exception
    {
        if (g == null)
        {
            throw new Exception("O Grupo passado est� nulo");
        }
        else if (u == null)
        {
            throw new Exception("O usu�rio passado est� nulo");
        }

        if (!g.isUserInGroup(u.getUserName()))
        {
            if (g.addUser(u.getUserName()))
            {
                g.save();
                return (Boolean.TRUE);
            }
        }

        return (Boolean.FALSE);
    }

    public static boolean removeUserFromGroup(UidfSession s, String user, String group) throws Exception
    {
        return (removeUserFromGroup(s.getIdfUser(user), s.getIdfGroup(group)));
    }

    public static boolean removeUserFromGroup(IDfUser u, IDfGroup g) throws Exception
    {
        if (g == null)
        {
            throw new Exception("O Grupo passado est� nulo");
        }
        else if (u == null)
        {
            throw new Exception("O usu�rio passado est� nulo");
        }
        else if (!g.isUserInGroup(u.getUserName()))
        {
            throw new Exception("O usu�rio passado n�o pertence ao grupo");
        }

        for (int i = 0; i < g.getAllUsersNamesCount(); i++)
        {
            if (g.getAllUsersNames(i) != null && g.getAllUsersNames(i).equals(u.getUserName()))
            {
                if (g.removeUser(u.getUserName()))
                {
                    g.save();
                    return (Boolean.TRUE);
                }
                else
                {
                    throw new Exception("N�o foi possivel remover o usu�rio " + g.getAllUsersNames(i) + " do grupo " + g.getGroupName());
                }
            }
        }

        return (Boolean.FALSE);
    }

}
