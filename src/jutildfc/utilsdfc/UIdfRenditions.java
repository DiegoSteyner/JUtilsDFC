package jutildfc.utilsdfc;

import jutildfc.data.VarsDfc;
import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfTypedObject;

public class UIdfRenditions extends AbstractUtilDfc
{
    public static final String PDF  = "pdf";
    public static final String HTML = "html";

    public UIdfRenditions(UidfSession session)
    {
        super(session);
    }

    public boolean isRenditionExist(String strObjectId, String strFormat) throws Exception
    {
        boolean bExists = false;

        if ((strObjectId == null) || (strFormat == null))
        {
            throw new IllegalArgumentException("Object ID and format must be valid!");
        }

        IDfCollection coll = null;
        IDfSysObject sysObj = getUidfSession().getIdfSysObject(strObjectId);

        if (sysObj.isReference())
        {
            sysObj = getUidfSession().getIdfSysObject(sysObj.getRemoteId());
        }
        coll = sysObj.getRenditions("");

        while ((coll.next()) && (!(bExists)))
        {
            IDfTypedObject typedObj = coll;
            if (strFormat.equals(typedObj.getString(VarsDfc.ATTR_FULL_FORMAT)))
            {
                bExists = true;
            }
        }
        return bExists;
    }

    public void queueToAutoRender(String strObjectId, String strFormat) throws Exception
    {
        if ((strObjectId == null) || (strFormat == null))
        {
            throw new IllegalArgumentException("Object ID and format must be valid!");
        }

        IDfSysObject sysObj = getUidfSession().getIdfSysObject(strObjectId);
        String application_code = sysObj.getString(VarsDfc.ATTR_A_CONTROLLING_APP);
        String message = null;

        if (strFormat.equals(PDF))
        {
            message = "rendition_req_ps_pdf";
            if ((application_code != null) && (application_code.trim().length() > 0))
            {
                message = message + " -application_code=" + application_code;
            }

            sysObj.queue("dm_autorender_win31", "rendition", 0, false, null, message);
        }
        else if (strFormat.equals(HTML))
        {
            message = "rendition_req_html:html:zip_html";
            if ((application_code != null) && (application_code.trim().length() > 0))
            {
                message = message + " -application_code=" + application_code;
            }

            sysObj.queue("dm_autorender_win31", "rendition", 0, false, null, message);
        }
    }

    public void queueToObjectsRenderingEngine(String strObjectId, String strFormat) throws Exception
    {
        if ((strObjectId == null) || (strFormat == null))
        {
            throw new IllegalArgumentException("Object ID and format must be valid!");
        }

        IDfSysObject sysObj = getUidfSession().getIdfSysObject(strObjectId);

        String application_code = sysObj.getString(VarsDfc.ATTR_A_CONTROLLING_APP);
        String message = null;
        if (strFormat.equals(PDF))
        {
            message = "rendition_req_ps_pdf";
            if ((application_code != null) && (application_code.trim().length() > 0))
            {
                message = message + " -application_code=" + application_code;
            }

            sysObj.queue("dm_autorender_win31", "rendition", 0, false, null, message);
        }
        else if (strFormat.equals(HTML))
        {
            message = "rendition_req_html:html:zip_html";
            if ((application_code != null) && (application_code.trim().length() > 0))
            {
                message = message + " -application_code=" + application_code;
            }

            sysObj.queue("dm_autorender_win31", "rendition", 0, false, null, message);
        }
    }
}