CCASE_SPEC = "\
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "element patterns /main/0", d)}%\
	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_3.22%\
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... ACT_PREREL-VIDEO-L23.16%\
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... ACT_PREREL-IMAGE-L23.16%\
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/lcml/... LINUX-MMSYSTEM_RLS_3.20%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_3.22%\
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... ACT_PREREL-APP-L23.16%\
	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_3.20%\
	# special hack needed because root of vob is not labeled:%\
	element /vobs/wtbu/OMAPSW_MPU /main/LATEST%\
	# don't pick up anything that is not labeled%\
	element * /main/0%\
	"
