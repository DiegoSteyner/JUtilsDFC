package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfGroup;

public class UidfAcl
{

    private UidfAcl()
    {

    }

    public static IDfGroup getGroupWithPermission(UidfSession s, IDfACL acl, int permission) throws Exception
    {
        for (int i = 0; i < acl.getAccessorCount(); i++)
        {
            if (acl.isGroup(i))
            {
                if (acl.getAccessorPermit(i) == permission)
                {
                    return (s.getIdfGroup(acl.getAccessorName(i)));
                }
            }
        }

        return (null);
    }

}
