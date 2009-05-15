CCASE_SPEC = "#****** SYSTEM ******%\
# Baseimage%\
element /vobs/wtbu/OMAPSW_DSP/system/baseimage/...  DSP-DASF-SYSTEM_RLS_${PV}%\
element /vobs/wtbu/OMAPSW_DSP/system/utils/...  DSP-DASF-SYSTEM_RLS_${PV}%\
# Build system%\
element /vobs/wtbu/OMAPSW_DSP/make/... DSP-DASF-SYSTEM_RLS_${PV}%\
# A/V Sync Task Node%\
element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/... DSP-DASF-SYSTEM_RLS_${PV}%\
# INST2%\
element /vobs/wtbu/OMAPSW_DSP/system/inst2/... DSP-DASF-SYSTEM_RLS_${PV}%\
element * /main/LATEST%\
"
