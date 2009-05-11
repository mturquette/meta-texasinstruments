CCASE_SPEC = "\
	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_${PV}%\
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... ACT-MMVIDEOPREREL-${PV}%\
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGEPREREL-${PV}%\
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_${PV}%\
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... ACT-MMAPPLICATIONPREREL-${PV}%\
	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_${PV}%\
	\
	element * /main/LATEST%\
	"
