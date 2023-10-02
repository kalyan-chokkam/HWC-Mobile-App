package org.piramalswasthya.cho.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.piramalswasthya.cho.database.room.dao.CaseRecordeDao
import org.piramalswasthya.cho.model.AssociateAilmentsHistory
import org.piramalswasthya.cho.model.DiagnosisCaseRecord
import org.piramalswasthya.cho.model.InvestigationCaseRecord
import org.piramalswasthya.cho.model.MedicationHistory
import org.piramalswasthya.cho.model.PrescriptionCaseRecord
import org.piramalswasthya.cho.model.VisitDB
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class CaseRecordeRepo @Inject constructor(
    private val caseRecordDao: CaseRecordeDao
) {
    suspend fun saveInvestigationToCatche(investigationCaseRecord: InvestigationCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertInvestigationCaseRecord(investigationCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Investigation $e")
        }
    }
    suspend fun saveDiagnosisToCatche(diagnosisCaseRecord: DiagnosisCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertDiagnosisCaseRecord(diagnosisCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Diagnosis $e")
        }
    }
    suspend fun getDiagnosisCaseRecordByBenRegIdAndPatientID(beneficiaryRegID: Long, patientID: String) : List<DiagnosisCaseRecord>?{
        return caseRecordDao.getDiagnosisCaseRecordeByBenRegIdAndPatientID(beneficiaryRegID, patientID)
    }
    suspend fun getInvestigationCaseRecordByBenRegIdAndPatientID(beneficiaryRegID: Long, patientID: String) : InvestigationCaseRecord?{
        return caseRecordDao.getInvestigationCaseRecordeByBenRegIdAndPatientID(beneficiaryRegID, patientID)
    }
    suspend fun getPrescriptionCaseRecordeByBenRegIdAndPatientID(beneficiaryRegID: Long, patientID: String) : List<PrescriptionCaseRecord>?{
        return caseRecordDao.getPrescriptionCaseRecordeByBenRegIdAndPatientID(beneficiaryRegID, patientID)
    }
    suspend fun savePrescriptionToCatche(prescriptionCaseRecord: PrescriptionCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertPrescriptionCaseRecord(prescriptionCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Prescription $e")
        }
    }
    fun getInvestigation(investigationId:String): LiveData<InvestigationCaseRecord> {
        return caseRecordDao.getInvestigationCasesRecordId(investigationId)
    }
    fun getPrescription(prescriptionId:String): LiveData<PrescriptionCaseRecord> {
        return caseRecordDao.getPrescriptionCasesRecordId(prescriptionId)
    }
    fun getDiagnosis(diagnosisId:String): LiveData<DiagnosisCaseRecord> {
        return caseRecordDao.getDiagnosisCasesRecordById(diagnosisId)
    }

    suspend fun updateBenIdAndBenRegId(beneficiaryID: Long, beneficiaryRegID: Long, patientID: String){
        caseRecordDao.updateBenIdBenRegIdPrescription(beneficiaryID, beneficiaryRegID, patientID)
        caseRecordDao.updateBenIdBenRegIdInvestigation(beneficiaryID, beneficiaryRegID, patientID)
        caseRecordDao.updateBenIdBenRegIdDiagnosis(beneficiaryID, beneficiaryRegID, patientID)
    }
    
}