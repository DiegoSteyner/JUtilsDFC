package jutildfc.data;

import java.util.HashMap;
import java.util.Map;

import com.documentum.fc.client.IDfWorkflow;
import com.documentum.operations.IDfDeleteOperation;
import com.documentum.operations.inbound.DfCheckinOperation;

public class VarsDfc {

//	public static final String SEPARADOR = System.getProperty("file.separator");
//	public static final String DIRECTORY_DEFAULT_CHECKOUT = "C:\\Documentum\\Checkout";
	
	/*
	 * FORMATOS
	 */
	public static final String FORMAT_WORD = "msw12";
	public static final String FORMAT_PDF  = "pdf";
	public static final String FORMAT_HTML = "html";
	
	/*
	 * ATRIBUTOS
	 */
	public static final String ATTR_ACL_DOMAIN 	   = "acl_domain";
	public static final String ATTR_ACL_NAME 	   = "acl_name";
	public static final String ATTR_AUTHORS 	   = "authors";
	public static final String ATTR_CABINET_ID 	   = "i_cabinet_id";
	public static final String ATTR_CHRONICLE_ID   = "i_chronicle_id";
	public static final String ATTR_CONTENT_SIZE   = "r_content_size";
	public static final String ATTR_CONTENT_TYPE   = "a_content_type";
	public static final String ATTR_CREATION_DATE  = "r_creation_date";
	public static final String ATTR_CREATOR_NAME   = "r_creator_name";
	public static final String ATTR_CURRENT_STATE  = "r_current_state";
	public static final String ATTR_ACCESSOR_NAME  = "r_accessor_name";
	public static final String ATTR_ACCESSOR_PERMIT  = "r_accessor_permit";
	public static final String ATTR_ACCESSOR_XPERMIT  = "r_accessor_xpermit";
	public static final String ATTR_IS_GROUP  = "r_is_group";
	public static final String ATTR_FOLDER_ID 	   = "i_folder_id";
	public static final String ATTR_GROUP_NAME 	   = "group_name";
	public static final String ATTR_GROUP_PERMIT   = "group_permit";
	public static final String ATTR_IS_VIRTUAL_DOC = "r_is_virtual_doc";
	public static final String ATTR_KEYWORDS 	   = "keywords";
	public static final String ATTR_LINK_COUNT     = "r_link_cnt";
	public static final String ATTR_LOCK_OWNER     = "r_lock_owner";
	public static final String ATTR_MODIFIER       = "r_modifier";
	public static final String ATTR_MODIFY_DATE    = "r_modify_date";
	public static final String ATTR_OBJECT_ID      = "r_object_id";
	public static final String ATTR_OBJECT_NAME    = "object_name";
	public static final String ATTR_OBJECT_TYPE    = "r_object_type";
	public static final String ATTR_OWNER_NAME     = "owner_name";
	public static final String ATTR_OWNER_PERMIT   = "owner_permit";
	public static final String ATTR_SUBJECT        = "subject";
	public static final String ATTR_TITLE          = "title";
	public static final String ATTR_VERSION_LABEL  = "r_version_label";
	public static final String ATTR_WORLD_PERMIT   = "world_permit";	
	public static final String ATTR_FOLDER_PATH    = "r_folder_path";
	public static final String ATTR_FULL_FORMAT    = "full_format";
	public static final String ATTR_I_FOLDER_ID    = "i_folder_id";
	public static final String ATTR_LINK_DEST_ACT  = "r_link_dest_act";
	public static final String ATTR_COMPONENT_ID   = "r_component_id";
	public static final String ATTR_A_CONTROLLING_APP   = "a_controlling_app";
	
	
	/*
	 * DOCUMENTUM OBJECT TYPES
	 */
	public static final String OBJ_TYPE_DM_TYPE_ACL					= "dm_acl";
	public static final String OBJ_TYPE_DM_ACS_CONFIG 				= "dm_acs_config";
	public static final String OBJ_TYPE_DM_ACTIVITY 				= "dm_activity";
	public static final String OBJ_TYPE_DM_AGGR_DOMAIN 				= "dm_aggr_domain";
	public static final String OBJ_TYPE_DM_ALIAS_SET 				= "dm_alias_set";
	public static final String OBJ_TYPE_DM_APP_REF 					= "dm_app_ref";
	public static final String OBJ_TYPE_DM_APPLICATION 				= "dm_application";
	public static final String OBJ_TYPE_DM_ASSEMBLY 				= "dm_assembly";
	public static final String OBJ_TYPE_DM_ATTACHMENTS_FOLDER 		= "dm_attachments_folder";
	public static final String OBJ_TYPE_DM_AUDITTRAIL 				= "dm_audittrail";
	public static final String OBJ_TYPE_DM_AUDITTRAIL_ACL 			= "dm_audittrail_acl";
	public static final String OBJ_TYPE_DM_AUDITTRAIL_GROUP 		= "dm_audittrail_group";
	public static final String OBJ_TYPE_DM_BLOBSTORE 				= "dm_blobstore";
	public static final String OBJ_TYPE_DM_BOCS_CONFIG 				= "dm_bocs_config";
	public static final String OBJ_TYPE_DM_BUILTIN_EXPR 			= "dm_builtin_expr";
	public static final String OBJ_TYPE_DM_BUSINESS_PRO 			= "dm_business_pro";
	public static final String OBJ_TYPE_DM_CA_STORE 				= "dm_ca_store";
	public static final String OBJ_TYPE_DM_CABINET 					= "dm_cabinet";
	public static final String OBJ_TYPE_DM_CACHE_CONFIG 			= "dm_cache_config";
	public static final String OBJ_TYPE_DM_CATEGORY 				= "dm_category";
	public static final String OBJ_TYPE_DM_CATEGORY_ASSIGN 			= "dm_category_assign";
	public static final String OBJ_TYPE_DM_CATEGORY_CLASS 			= "dm_category_class";
	public static final String OBJ_TYPE_DM_CI_CONFIG 				= "dm_ci_config";
	public static final String OBJ_TYPE_DM_CLIENT_REGISTRATION 		= "dm_client_registration";
	public static final String OBJ_TYPE_DM_CLIENT_RIGHTS 			= "dm_client_rights";
	public static final String OBJ_TYPE_DM_COMPONENT 				= "dm_component";
	public static final String OBJ_TYPE_DM_COND_EXPR 				= "dm_cond_expr";
	public static final String OBJ_TYPE_DM_COND_ID_EXPR 			= "dm_cond_id_expr";
	public static final String OBJ_TYPE_DM_CONT_TRANSFER_CONFIG 	= "dm_cont_transfer_config";
	public static final String OBJ_TYPE_DM_CRYPTOGRAPHIC_KEY 		= "dm_cryptographic_key";
	public static final String OBJ_TYPE_DM_DD_INFO 					= "dm_dd_info";
	public static final String OBJ_TYPE_DM_DECISION 				= "dm_decision";
	public static final String OBJ_TYPE_DM_DISPLAY_CONFIG 			= "dm_display_config";
	public static final String OBJ_TYPE_DM_DISTRIBUTEDSTORE 		= "dm_distributedstore";
	public static final String OBJ_TYPE_DM_DMS_CONFIG 				= "dm_dms_config";
	public static final String OBJ_TYPE_DM_DOCBASE_CONFIG 			= "dm_docbase_config";
	public static final String OBJ_TYPE_DM_DOCBASEID_MAP 			= "dm_docbaseid_map";
	public static final String OBJ_TYPE_DM_DOCSET 					= "dm_docset";
	public static final String OBJ_TYPE_DM_DOCSET_RUN 				= "dm_docset_run";
	public static final String OBJ_TYPE_DM_DOCUMENT 				= "dm_document";
	public static final String OBJ_TYPE_DM_DOMAIN 					= "dm_domain";
	public static final String OBJ_TYPE_DM_DUMP_RECORD 				= "dm_dump_record";
	public static final String OBJ_TYPE_DM_EMAIL_MESSAGE 			= "dm_email_message";
	public static final String OBJ_TYPE_DM_ESIGN_TEMPLATE 			= "dm_esign_template";
	public static final String OBJ_TYPE_DM_EXPRESSION 				= "dm_expression";
	public static final String OBJ_TYPE_DM_EXTERN_FILE 				= "dm_extern_file";
	public static final String OBJ_TYPE_DM_EXTERN_FREE	 			= "dm_extern_free";
	public static final String OBJ_TYPE_DM_EXTERN_STORE 			= "dm_extern_store";
	public static final String OBJ_TYPE_DM_EXTERN_URL 				= "dm_extern_url";
	public static final String OBJ_TYPE_DM_FEDERATION 				= "dm_federation";
	public static final String OBJ_TYPE_DM_FILESTORE 				= "dm_filestore";
	public static final String OBJ_TYPE_DM_FOLDER 					= "dm_folder";
	public static final String OBJ_TYPE_DM_FOREIGN_KEY 				= "dm_foreign_key";
	public static final String OBJ_TYPE_DM_FORMAT 					= "dm_format";
	public static final String OBJ_TYPE_DM_FORMAT_PREFERENCES 		= "dm_format_preferences";
	public static final String OBJ_TYPE_DM_FTENGINE_CONFIG 			= "dm_ftengine_config";
	public static final String OBJ_TYPE_DM_FTINDEX_AGENT_CONFIG 	= "dm_ftindex_agent_config";
	public static final String OBJ_TYPE_DM_FULLTEXT_INDEX 			= "dm_fulltext_index";
	public static final String OBJ_TYPE_DM_FUNC_EXPR 				= "dm_func_expr";
	public static final String OBJ_TYPE_DM_GROUP 					= "dm_group";
	public static final String OBJ_TYPE_DM_JAVA 					= "dm_java";
	public static final String OBJ_TYPE_DM_JMS_CONFIG 				= "dm_jms_config";
	public static final String OBJ_TYPE_DM_JOB 						= "dm_job";
	public static final String OBJ_TYPE_DM_JOB_REQUEST 				= "dm_job_request";
	public static final String OBJ_TYPE_DM_JOB_SEQUENCE 			= "dm_job_sequence";
	public static final String OBJ_TYPE_DM_KEY 						= "dm_key";
	public static final String OBJ_TYPE_DM_LDAP_CONFIG 				= "dm_ldap_config";
	public static final String OBJ_TYPE_DM_LINKEDSTORE 				= "dm_linkedstore";
	public static final String OBJ_TYPE_DM_LITERAL_EXPR 			= "dm_literal_expr";
	public static final String OBJ_TYPE_DM_LOAD_RECORD 				= "dm_load_record";
	public static final String OBJ_TYPE_DM_LOCATION 				= "dm_location";
	public static final String OBJ_TYPE_DM_LOCATOR 					= "dm_locator";
	public static final String OBJ_TYPE_DM_MEDIA_PROFILE 			= "dm_media_profile";
	public static final String OBJ_TYPE_DM_MENU_SYSTEM 				= "dm_menu_system";
	public static final String OBJ_TYPE_DM_MESSAGE_ADDRESS 			= "dm_message_address";
	public static final String OBJ_TYPE_DM_MESSAGE_ARCHIVE          = "dm_message_archive";
	public static final String OBJ_TYPE_DM_MESSAGE_ATTACHMENT       = "dm_message_attachment";
	public static final String OBJ_TYPE_DM_MESSAGE_CONTAINER        = "dm_message_container";
	public static final String OBJ_TYPE_DM_MESSAGE_ROUTE_USER_DATA  = "dm_message_route_user_data";
	public static final String OBJ_TYPE_DM_MESSAGE_USER_DATA        = "dm_message_user_data";
	public static final String OBJ_TYPE_DM_METHOD                   = "dm_method";
	public static final String OBJ_TYPE_DM_MIGRATE_RULE             = "dm_migrate_rule";
	public static final String OBJ_TYPE_DM_MOUNT_POINT              = "dm_mount_point";
	public static final String OBJ_TYPE_DM_NETWORK_LOCATION_MAP     = "dm_network_location_map";
	public static final String OBJ_TYPE_DM_NLS_DD_INFO              = "dm_nls_dd_info";
	public static final String OBJ_TYPE_DM_NOTE                     = "dm_note";
	public static final String OBJ_TYPE_DM_OPTICALSTORE             = "dm_opticalstore";
	public static final String OBJ_TYPE_DM_OUTPUTDEVICE             = "dm_outputdevice";
	public static final String OBJ_TYPE_DM_PLUGIN                   = "dm_plugin";
	public static final String OBJ_TYPE_DM_POLICY                   = "dm_policy";
	public static final String OBJ_TYPE_DM_PROCEDURE                = "dm_procedure";
	public static final String OBJ_TYPE_DM_PROCESS                  = "dm_process";
	public static final String OBJ_TYPE_DM_PUBLIC_KEY_CERTIFICATE   = "dm_public_key_certificate";
	public static final String OBJ_TYPE_DM_QUAL_COMP                = "dm_qual_comp";
	public static final String OBJ_TYPE_DM_QUERY                    = "dm_query";
	public static final String OBJ_TYPE_DM_REFERENCE                = "dm_reference";
	public static final String OBJ_TYPE_DM_REGISTERED               = "dm_registered";
	public static final String OBJ_TYPE_DM_RELATION                 = "dm_relation";
	public static final String OBJ_TYPE_DM_RELATION_SSA_POLICY      = "dm_relation_ssa_policy";
	public static final String OBJ_TYPE_DM_RELATION_TYPE            = "dm_relation_type";
	public static final String OBJ_TYPE_DM_RETAINER                 = "dm_retainer";
	public static final String OBJ_TYPE_DM_ROUTER                   = "dm_router";
	public static final String OBJ_TYPE_DM_SCOPE_CONFIG             = "dm_scope_config";
	public static final String OBJ_TYPE_DM_SCRIPT                   = "dm_script";
	public static final String OBJ_TYPE_DM_SERVER_CONFIG            = "dm_server_config";
	public static final String OBJ_TYPE_DM_SHMECONFIG               = "dm_shmeconfig";
	public static final String OBJ_TYPE_DM_SMART_LIST               = "dm_smart_list";
	public static final String OBJ_TYPE_DM_SSA_POLICY               = "dm_ssa_policy";
	public static final String OBJ_TYPE_DM_STAGED                   = "dm_staged";
	public static final String OBJ_TYPE_DM_STATE_EXTENSION          = "dm_state_extension";
	public static final String OBJ_TYPE_DM_STATE_TYPE               = "dm_state_type";
	public static final String OBJ_TYPE_DM_STORE                    = "dm_store";
	public static final String OBJ_TYPE_DM_SYNC_LIST_RELATION       = "dm_sync_list_relation";
	public static final String OBJ_TYPE_DM_SYSOBJECT                = "dm_sysobject";
	public static final String OBJ_TYPE_DM_SYSPROCESS_CONFIG        = "dm_sysprocess_config";
	public static final String OBJ_TYPE_DM_TAXONOMY                 = "dm_taxonomy";
	public static final String OBJ_TYPE_DM_TYPE                     = "dm_type";
	public static final String OBJ_TYPE_DM_USER                     = "dm_user";
	public static final String OBJ_TYPE_DM_VALIDATION_DESCRIPTOR    = "dm_validation_descriptor";
	public static final String OBJ_TYPE_DM_VALUE_ASSIST             = "dm_value_assist";
	public static final String OBJ_TYPE_DM_VALUE_FUNC               = "dm_value_func";
	public static final String OBJ_TYPE_DM_VALUE_LIST               = "dm_value_list";
	public static final String OBJ_TYPE_DM_VALUE_QUERY              = "dm_value_query";
	public static final String OBJ_TYPE_DM_WEBC_CONFIG              = "dm_webc_config";
	public static final String OBJ_TYPE_DM_WEBC_TARGET              = "dm_webc_target";
	public static final String OBJ_TYPE_DM_WORKFLOW                 = "dm_workflow";
	public static final String OBJ_TYPE_DM_XML_APPLICATION          = "dm_xml_application";
	public static final String OBJ_TYPE_DM_XML_CONFIG               = "dm_xml_config";
	public static final String OBJ_TYPE_DM_XML_CUSTOM_CODE          = "dm_xml_custom_code";
	public static final String OBJ_TYPE_DM_XML_STYLE_SHEET          = "dm_xml_style_sheet";
	public static final String OBJ_TYPE_DM_XML_ZONE                 = "dm_xml_zone";
	public static final String OBJ_TYPE_DMC_ACT_GROUP_INSTANCE      = "dmc_act_group_instance";
	public static final String OBJ_TYPE_DMC_ASPECT_RELATION         = "dmc_aspect_relation";
	public static final String OBJ_TYPE_DMC_ASPECT_TYPE             = "dmc_aspect_type";
	public static final String OBJ_TYPE_DMC_CLASS                   = "dmc_class";
	public static final String OBJ_TYPE_DMC_COMPLETED_WORKFLOW      = "dmc_completed_workflow";
	public static final String OBJ_TYPE_DMC_COMPLETED_WORKITEM      = "dmc_completed_workitem";
	public static final String OBJ_TYPE_DMC_COMPOSITE_PREDICATE     = "dmc_composite_predicate";
	public static final String OBJ_TYPE_DMC_CONFIG_SCOPE_RELATION   = "dmc_config_scope_relation";
	public static final String OBJ_TYPE_DMC_CONSTRAINT_SET          = "dmc_constraint_set";
	public static final String OBJ_TYPE_DMC_DAR                     = "dmc_dar";
	public static final String OBJ_TYPE_DMC_JAR                     = "dmc_jar";
	public static final String OBJ_TYPE_DMC_JAVA_LIBRARY            = "dmc_java_library";
	public static final String OBJ_TYPE_DMC_METAMODEL               = "dmc_metamodel";
	public static final String OBJ_TYPE_DMC_MODULE                  = "dmc_module";
	public static final String OBJ_TYPE_DMC_MODULE_CONFIG           = "dmc_module_config";
	public static final String OBJ_TYPE_DMC_PRESET_INFO             = "dmc_preset_info";
	public static final String OBJ_TYPE_DMC_PRESET_PACKAGE          = "dmc_preset_package";
	public static final String OBJ_TYPE_DMC_PROCESS_CORRELATION_SET = "dmc_process_correlation_set";
	public static final String OBJ_TYPE_DMC_PROCESS_PARAMETER       = "dmc_process_parameter";
	public static final String OBJ_TYPE_DMC_RELATIONSHIP_DEF        = "dmc_relationship_def";
	public static final String OBJ_TYPE_DMC_ROUTECASE_CONDITION     = "dmc_routecase_condition";
	public static final String OBJ_TYPE_DMC_TCF_ACTIVITY            = "dmc_tcf_activity";
	public static final String OBJ_TYPE_DMC_TCF_ACTIVITY_TEMPLATE   = "dmc_tcf_activity_template";
	public static final String OBJ_TYPE_DMC_TRANSITION_CONDITION    = "dmc_transition_condition";
	public static final String OBJ_TYPE_DMC_VALIDATION_MODULE       = "dmc_validation_module";
	public static final String OBJ_TYPE_DMC_VALIDATION_RELATION     = "dmc_validation_relation";
	public static final String OBJ_TYPE_DMC_WF_PACKAGE_SCHEMA       = "dmc_wf_package_schema";
	public static final String OBJ_TYPE_DMC_WF_PACKAGE_SKILL        = "dmc_wf_package_skill";
	public static final String OBJ_TYPE_DMC_WF_PACKAGE_TYPE_INFO    = "dmc_wf_package_type_info";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT            = "dmc_wfsd_element";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_BOOLEAN    = "dmc_wfsd_element_boolean";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_DATE       = "dmc_wfsd_element_date";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_DOUBLE 	= "dmc_wfsd_element_double";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_INTEGER 	= "dmc_wfsd_element_integer";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_PARENT 	= "dmc_wfsd_element_parent";
	public static final String OBJ_TYPE_DMC_WFSD_ELEMENT_STRING 	= "dmc_wfsd_element_string";
	public static final String OBJ_TYPE_DMC_WFSD_TYPE_INFO 			= "dmc_wfsd_type_info";
	public static final String OBJ_TYPE_DMC_WFSDRP_BOOLEAN 			= "dmc_wfsdrp_boolean";
	public static final String OBJ_TYPE_DMC_WFSDRP_DATE 			= "dmc_wfsdrp_date";
	public static final String OBJ_TYPE_DMC_WFSDRP_DOUBLE 			= "dmc_wfsdrp_double";
	public static final String OBJ_TYPE_DMC_WFSDRP_INTEGER 			= "dmc_wfsdrp_integer";
	public static final String OBJ_TYPE_DMC_WFSDRP_PARENT 			= "dmc_wfsdrp_parent";
	public static final String OBJ_TYPE_DMC_WFSDRP_STRING 			= "dmc_wfsdrp_string";
	public static final String OBJ_TYPE_DMC_WORKQUEUE 				= "dmc_workqueue";
	public static final String OBJ_TYPE_DMC_WORKQUEUE_CATEGORY 		= "dmc_workqueue_category";
	public static final String OBJ_TYPE_DMC_WORKQUEUE_DOC_PROFILE 	= "dmc_workqueue_doc_profile";
	public static final String OBJ_TYPE_DMC_WORKQUEUE_POLICY 		= "dmc_workqueue_policy";
	public static final String OBJ_TYPE_DMC_WORKQUEUE_USER_PROFILE 	= "dmc_workqueue_user_profile";
	public static final String OBJ_TYPE_DMC_WPR_PARENT 				= "dmc_wpr_parent";
	public static final String OBJ_TYPE_DMC_WQ_SKILL_INFO 			= "dmc_wq_skill_info";
	public static final String OBJ_TYPE_DMC_WQ_TASK_SKILL 			= "dmc_wq_task_skill";
	public static final String OBJ_TYPE_DMC_WQ_USER_SKILL	 		= "dmc_wq_user_skill";
	public static final String OBJ_TYPE_DMI_AUDITTRAIL_ATTRS 		= "dmi_audittrail_attrs";
	public static final String OBJ_TYPE_DMI_CHANGE_RECORD 			= "dmi_change_record";
	public static final String OBJ_TYPE_DMI_DD_ATTR_INFO 			= "dmi_dd_attr_info";
	public static final String OBJ_TYPE_DMI_DD_COMMON_INFO 			= "dmi_dd_common_info";
	public static final String OBJ_TYPE_DMI_DD_TYPE_INFO 			= "dmi_dd_type_info";
	public static final String OBJ_TYPE_DMI_DIST_COMP_RECORD 		= "dmi_dist_comp_record";
	public static final String OBJ_TYPE_DMI_DUMP_OBJECT_RECORD 		= "dmi_dump_object_record";
	public static final String OBJ_TYPE_DMI_EXPR_CODE 				= "dmi_expr_code";
	public static final String OBJ_TYPE_DMI_INDEX 					= "dmi_index";
	public static final String OBJ_TYPE_DMI_LINKRECORD 				= "dmi_linkrecord";
	public static final String OBJ_TYPE_DMI_LOAD_OBJECT_RECORD 		= "dmi_load_object_record";
	public static final String OBJ_TYPE_DMI_OTHERFILE 				= "dmi_otherfile";
	public static final String OBJ_TYPE_DMI_PACKAGE 				= "dmi_package";
	public static final String OBJ_TYPE_DMI_QUEUE_ITEM 				= "dmi_queue_item";
	public static final String OBJ_TYPE_DMI_RECOVERY 				= "dmi_recovery";
	public static final String OBJ_TYPE_DMI_REGISTRY 				= "dmi_registry";
	public static final String OBJ_TYPE_DMI_REPLICA_RECORD 			= "dmi_replica_record";
	public static final String OBJ_TYPE_DMI_SEQUENCE 				= "dmi_sequence";
	public static final String OBJ_TYPE_DMI_SESSION 				= "dmi_session";
	public static final String OBJ_TYPE_DMI_SUBCONTENT 				= "dmi_subcontent";
	public static final String OBJ_TYPE_DMI_TRANSACTIONLOG 			= "dmi_transactionlog";
	public static final String OBJ_TYPE_DMI_TYPE_INFO 				= "dmi_type_info";
	public static final String OBJ_TYPE_DMI_VSTAMP 					= "dmi_vstamp";
	public static final String OBJ_TYPE_DMI_WF_ATTACHMENT 			= "dmi_wf_attachment";
	public static final String OBJ_TYPE_DMI_WF_TIMER 				= "dmi_wf_timer";
	public static final String OBJ_TYPE_DMI_WORKITEM 				= "dmi_workitem";
	public static final String OBJ_TYPE_DMR_CONTAINMENT 			= "dmr_containment";
	public static final String OBJ_TYPE_DMR_CONTENT 				= "dmr_content";
	public static final String OBJ_TYPE_DRS_REPORT 					= "drs_report";
	
	/*
	 * CABINETS
	 */
	public static final String CABINET_INTEGRATION  = "/Integration";
	public static final String CABINET_REPORTS 		= "/Reports";
	public static final String CABINET_RESOURCES 	= "/Resources";
	public static final String CABINET_SYSTEM 		= "/System";
	public static final String CABINET_TEMP 		= "/Temp";
	public static final String CABINET_TEMPLATES 	= "/Templates";
	
	
	/*
	 * LOCALIZACAO UTEIS
	 */
	public static final String LOCATION_ASPECT 		= "/System/Modules/Aspect";
	public static final String LOCATION_PREFERENCES = "/Resources/Registry/Preferences";
	public static final String LOCATION_PRESETS 	= "/Resources/Registry/Presets";
	public static final String LOCATION_SBO 		= "/System/Modules/SBO";
	public static final String LOCATION_TBO 		= "/System/Modules/TBO";
	
	
	/*
	 * CLIENT CAPABILITY
	 */
	public static final int INT_CAPABILITY_CONSUMER2	= 0;
	public static final int INT_CAPABILITY_CONSUMER 	= 1;
	public static final int INT_CAPABILITY_CONTRIBUTOR 	= 2;
	public static final int INT_CAPABILITY_COORDINATOR 	= 4;
	public static final int INT_CAPABILITY_SYSTEM_ADMIN = 8;

	public static final String STR_CAPABILITE_CONSUMER	 		 = "CONSUMER";
	public static final String STR_CAPABILITE_CONTRIBUTOR	 	 = "CONTRIBUTOR";
	public static final String STR_CAPABILITE_COORDINATOR	 	 = "COORDINATOR";
	public static final String STR_CAPABILITE_SYS_ADMINISTRATOR	 = "SYSTEM ADMINISTRATOR";
	
	/*
	 * USER PRIVILEGES
	 */
	public static final int INT_PRIVILEGE_NONE				= 0;
	public static final int INT_PRIVILEGE_CREATE_TYPE		= 1;
	public static final int INT_PRIVILEGE_CREATE_CABINET	= 2;
	public static final int INT_PRIVILEGE_CREATE_GROUP		= 4;
	public static final int INT_PRIVILEGE_SYS_ADMIN			= 8;
	public static final int INT_PRIVILEGE_SUPER_USER		= 16;
	
	
	public static final String STR_PRIVILEGE_NONE			= "NONE";
	public static final String STR_PRIVILEGE_CREATE_TYPE	= "CREATE TYPE";
	public static final String STR_PRIVILEGE_CREATE_CABINET	= "CREATE CABINET";
	public static final String STR_PRIVILEGE_CREATE_GROUP	= "CREATE GROUP";
	public static final String STR_PRIVILEGE_SYS_ADMIN		= "SYSADMIN";
	public static final String STR_PRIVILEGE_SUPER_USER		= "SUPERUSER";
	
	
	/*
	 * PERMISSOES BASICAS
	 */
	public static final int INT_BASIC_PERMISSION_NONE 	 = 1;
	public static final int INT_BASIC_PERMISSION_BROWSE  = 2;
	public static final int INT_BASIC_PERMISSION_READ    = 3;
	public static final int INT_BASIC_PERMISSION_NOTE    = 4;
	public static final int INT_BASIC_PERMISSION_VERSION = 5;
	public static final int INT_BASIC_PERMISSION_WRITE   = 6;
	public static final int INT_BASIC_PERMISSION_DELETE  = 7;
	
	
	public static final String STR_BASIC_PERMISSION_NONE       = "NONE";
	public static final String STR_BASIC_PERMISSION_BROWSE     = "BROWSE";
	public static final String STR_BASIC_PERMISSION_READ       = "READ";
	public static final String STR_BASIC_PERMISSION_NOTE       = "NOTE";
	public static final String STR_BASIC_PERMISSION_VERSION    = "VERSION";
	public static final String STR_BASIC_PERMISSION_WRITE      = "WRITE";
	public static final String STR_BASIC_PERMISSION_DELETE     = "DELETE";
	
	/*
	 * PERMISSOES EXTENDIDAS 
	 */
	public static final int INT_EXTENDED_PERMISSION_CHANGE_STATE    = 1;
	public static final int INT_EXTENDED_PERMISSION_PERMIT          = 2;
	public static final int INT_EXTENDED_PERMISSION_CHANGE_OWNER    = 3;
	public static final int INT_EXTENDED_PERMISSION_EXECUTE_PROC    = 4;
	public static final int INT_EXTENDED_PERMISSION_CHANGE_LOCATION = 5;
	
	public static final String STR_EXTENDED_PERMISSION_CHANGE_STATE    = "CHANGE_STATE";
	public static final String STR_EXTENDED_PERMISSION_CHANGE_PERMIT   = "CHANGE_PERMIT";
	public static final String STR_EXTENDED_PERMISSION_CHANGE_OWNER    = "CHANGE_OWNER";
	public static final String STR_EXTENDED_PERMISSION_EXECUTE_PROC    = "EXECUTE_PROC";
	public static final String STR_EXTENDED_PERMISSION_CHANGE_LOCATION = "CHANGE_LOCATION";
	
	/*
	 * USER STATE 
	 */
	public static final int INT_USER_PERMIT_LOGIN    	= 0;
	public static final int INT_USER_NOT_PERMIT_LOGIN 	= 1;
	public static final int INT_USER_LOCKED_USER    	= 2;
	public static final int INT_USER_LOCKED_AND_INATIVE	= 3;

	public static final String STR_USER_PERMIT_LOGIN    	= "LOGIN PERMIT";
	public static final String STR_USER_NOT_PERMIT_LOGIN 	= "LOGIN NOT PERMIT";
	public static final String STR_USER_LOCKED_USER    		= "USER IN LOCK";
	public static final String STR_USER_LOCKED_AND_INATIVE	= "USER IN LOCK AND INACTIVE";
	
	
	/*
	 * ESTADOS DOS WORKFLOWS 
	 */
	public static final int WORKFLOW_STATE_RUNNING    = IDfWorkflow.DF_WF_STATE_RUNNING;
	public static final int WORKFLOW_STATE_HALTED     = IDfWorkflow.DF_WF_STATE_HALTED;
	public static final int WORKFLOW_STATE_DORMANT    = IDfWorkflow.DF_WF_STATE_DORMANT;
	public static final int WORKFLOW_STATE_TERMINATED = IDfWorkflow.DF_WF_STATE_TERMINATED;
	
	/*
	 * AVULSOS 
	 */
	
	public static final String LABEL_CURRENT = "CURRENT";
	
	public static final int CHECK_IN_NEXT_MAJOR      = DfCheckinOperation.NEXT_MAJOR;
	public static final int CHECK_IN_NEXT_MINOR      = DfCheckinOperation.NEXT_MINOR;
	public static final int CHECK_IN_SAME_VERSION    = DfCheckinOperation.SAME_VERSION;
	public static final int CHECK_IN_VERSION_NOT_SET = DfCheckinOperation.VERSION_NOT_SET;
	public static final int CHECK_IN_BRANCH          = DfCheckinOperation.BRANCH_VERSION;
	
	public static final int DELETE_ALL_VERSIONS      = IDfDeleteOperation.ALL_VERSIONS;
	public static final int DELETE_SELECTED_VERSIONS = IDfDeleteOperation.SELECTED_VERSIONS;
	public static final int DELETE_UNUSED_VERSIONS   = IDfDeleteOperation.UNUSED_VERSIONS;
	
	public static final String REPLACE_ID_DQL   = "REPLACEID";
	public static final String REPLACE_NAME_DQL = "REPLACENAME";
	public static final String REPLACE_PATH_DQL = "REPLACEPATH";
	
	
	public static final String DQL_FOLDER_PATH = "select ".concat(ATTR_FOLDER_PATH).concat(",").concat(ATTR_OBJECT_ID)
														  .concat(" from dm_folder where ").concat(ATTR_OBJECT_ID)
														  .concat(" in (")
													  		.concat("select ").concat(ATTR_I_FOLDER_ID).concat(" from dm_document ")
													  		.concat("where ").concat(ATTR_OBJECT_ID).concat(" = '").concat(REPLACE_ID_DQL)
														  .concat("')");

	public static final String DQL_OBJECT_IN_FOLDER = "select ".concat(ATTR_OBJECT_ID)
															   .concat(" from dm_sysobject where ").concat(ATTR_OBJECT_NAME)
															   .concat(" like '%").concat(REPLACE_NAME_DQL)
															   .concat("%' and folder('").concat(REPLACE_PATH_DQL).concat("', descend)");
	
	public static final String DQL_GET_TYPES_NAMES 		 	= "SELECT r_type_name FROM dmi_type_info";
	public static final String DQL_DQL_GET_WORKITEM_FROM_WF = "SELECT r_object_id FROM dmi_workitem WHERE r_runtime_state in (0,1) AND r_workflow_id='".concat(REPLACE_ID_DQL).concat("'");
	public static final String DQL_GET_ACTIVITY_CURRENT  	= "SELECT r_act_def_id from dmi_workitem WHERE r_runtime_state in (0,1,3) AND r_workflow_id = '".concat(REPLACE_ID_DQL).concat("'");
	public static final String DQL_ALL_RUNNING_WORKFLOWS 	= "SELECT w.r_object_id, w.supervisor_name, w.r_start_date, w.object_name AS workflow_name, p.object_name AS process_name FROM dm_workflow w, dm_process p WHERE w.process_id = p.r_object_id AND w.r_runtime_state in (0,1,3)";
	
	public static final Map<Integer, String> BASIC_PERMISSIONS    = new HashMap<Integer, String>();
	public static final Map<Integer, String> EXTENDED_PERMISSIONS = new HashMap<Integer, String>();
	public static final Map<Integer, String> CLIENTS_CAPABILITIES = new HashMap<Integer, String>();
	public static final Map<Integer, String> USER_PRIVILEGES 	  = new HashMap<Integer, String>();
	public static final Map<Integer, String> USER_STATE 		  = new HashMap<Integer, String>();
	
	static{
		
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_NONE, STR_BASIC_PERMISSION_NONE);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_BROWSE, STR_BASIC_PERMISSION_BROWSE);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_READ, STR_BASIC_PERMISSION_READ);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_NOTE, STR_BASIC_PERMISSION_NOTE);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_VERSION, STR_BASIC_PERMISSION_VERSION);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_WRITE, STR_BASIC_PERMISSION_WRITE);
		BASIC_PERMISSIONS.put(INT_BASIC_PERMISSION_DELETE, STR_BASIC_PERMISSION_DELETE);
		
		EXTENDED_PERMISSIONS.put(INT_EXTENDED_PERMISSION_CHANGE_STATE, STR_EXTENDED_PERMISSION_CHANGE_STATE);
		EXTENDED_PERMISSIONS.put(INT_EXTENDED_PERMISSION_PERMIT, STR_EXTENDED_PERMISSION_CHANGE_PERMIT);
		EXTENDED_PERMISSIONS.put(INT_EXTENDED_PERMISSION_CHANGE_OWNER, STR_EXTENDED_PERMISSION_CHANGE_OWNER);
		EXTENDED_PERMISSIONS.put(INT_EXTENDED_PERMISSION_EXECUTE_PROC, STR_EXTENDED_PERMISSION_EXECUTE_PROC);
		EXTENDED_PERMISSIONS.put(INT_EXTENDED_PERMISSION_CHANGE_LOCATION, STR_EXTENDED_PERMISSION_CHANGE_LOCATION);
		
		CLIENTS_CAPABILITIES.put(INT_CAPABILITY_CONSUMER, STR_CAPABILITE_CONSUMER);
		CLIENTS_CAPABILITIES.put(INT_CAPABILITY_CONSUMER2, STR_CAPABILITE_CONSUMER);
		CLIENTS_CAPABILITIES.put(INT_CAPABILITY_CONTRIBUTOR, STR_CAPABILITE_CONTRIBUTOR);
		CLIENTS_CAPABILITIES.put(INT_CAPABILITY_COORDINATOR, STR_CAPABILITE_COORDINATOR);
		CLIENTS_CAPABILITIES.put(INT_CAPABILITY_SYSTEM_ADMIN, STR_CAPABILITE_SYS_ADMINISTRATOR);
		
		USER_PRIVILEGES.put(INT_PRIVILEGE_NONE, STR_PRIVILEGE_NONE);
		USER_PRIVILEGES.put(INT_PRIVILEGE_CREATE_CABINET, STR_PRIVILEGE_CREATE_CABINET);
		USER_PRIVILEGES.put(INT_PRIVILEGE_CREATE_GROUP, STR_PRIVILEGE_CREATE_GROUP);
		USER_PRIVILEGES.put(INT_PRIVILEGE_CREATE_TYPE, STR_PRIVILEGE_CREATE_TYPE);
		USER_PRIVILEGES.put(INT_PRIVILEGE_SUPER_USER, STR_PRIVILEGE_SUPER_USER);
		USER_PRIVILEGES.put(INT_PRIVILEGE_SYS_ADMIN, STR_PRIVILEGE_SYS_ADMIN);
		
		USER_STATE.put(INT_USER_PERMIT_LOGIN, STR_USER_PERMIT_LOGIN);
		USER_STATE.put(INT_USER_NOT_PERMIT_LOGIN, STR_USER_NOT_PERMIT_LOGIN);
		USER_STATE.put(INT_USER_LOCKED_USER, STR_USER_LOCKED_USER);
		USER_STATE.put(INT_USER_LOCKED_AND_INATIVE, STR_USER_LOCKED_AND_INATIVE);
	}
	
	public static boolean isBasicPermission(int permission) throws Exception
	{
		return(BASIC_PERMISSIONS.containsKey(permission));
	}

	public static boolean isExtendsPermission(int permission) throws Exception
	{
		return(EXTENDED_PERMISSIONS.containsKey(permission));
	}
	
	public static String getPermission(int permission) throws Exception
	{
		if(isBasicPermission(permission))
		{
			return(getNameBasicPermission(permission));
		}
		if(isExtendsPermission(permission))
		{
			return(getNameExtendsPermission(permission));
		}
		return("Permission Not Found");
	}
	
	public static String getNameBasicPermission(int permission) throws Exception
	{
		return(BASIC_PERMISSIONS.get(permission));
	}

	public static String getNameExtendsPermission(int permission) throws Exception
	{
		return(EXTENDED_PERMISSIONS.get(permission));
	}

	public static String getNameClientCapabilities(int permission) throws Exception
	{
		return(CLIENTS_CAPABILITIES.get(permission));
	}

	public static String getUserPrivileges(int permission) throws Exception
	{
		return(USER_PRIVILEGES.get(permission));
	}

	public static String getUserState(int permission) throws Exception
	{
		return(USER_STATE.get(permission));
	}
}
