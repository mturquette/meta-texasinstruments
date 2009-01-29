# This fetcher class requires the following:
# ${DIRAC_SPEC} -		A ClearCase config spec
# ${DIRAC_PATHFETCH} -		The elements (files and directories) to fetch
# ${DIRAC_PATHCOMPONENT} -	The very last part of the PATH in ClearCase
#				(e.g. "gstreamer" in
#				/vobs/wtbu/OMAPSW_L/mmframework/libs/gstreamer)
# ${DIRAC_PATHCOMPONENTS} -	The number of path components to strip. E.g.:
#				5 for {vobs, wtbu, OMAPSW_L, mmframework, libs}
#				leaving just "gstreamer" when untarring.
##
# The config spec must change new-lines with '%' (percentage signs):
#   DIRAC_SPEC = "\
#    element /vobs/wtbu/OMAPSW_L/mmframework MMFRAMEWORK_REL_4.20%\
#    element * /main/latest%"
# (Don't forget to escape the recipe's new-lines anyway.) Or this too:
#   DIRAC_SPEC = "element /vobs/wtbu/OMAPSW_L/mmframework MMFRAMEWORK_REL_4.20%element * /main/latest%"
#

inherit base

do_fetch_dirac () {
	if [ -e "${DL_DIR}/${PN}-${PV}.tar.gz" ]; then
		echo "VOB elements already fetched."
	else
		cd ${DL_DIR}
		echo "fetching ${DIRAC_PATHCOMPONENT} from ${DIRAC_PATHFETCH}"
		cfetchdirac.sh ${DIRAC_PATHFETCH} > ${DL_DIR}/${PN}-${PV}.tar.gz
	fi
}

do_unpack_dirac () {
	[ ! -z "${DIRAC_PATHCOMPONENTS}" ] && TARSTRIP="--strip-components ${DIRAC_PATHCOMPONENTS}"
	mkdir -p ${S}
	cd ${S}
	tar zxf ${DL_DIR}/${PN}-${PV}.tar.gz ${TARSTRIP}
#	mv ${DIRAC_PATHCOMPONENT} ${PN}-${PV}
}

addtask fetch_dirac after do_fetch before do_unpack
addtask unpack_dirac after do_unpack before do_patch
