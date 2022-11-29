package com.kkcloud.risk.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.kkcloud.risk.model.RiskEntryExport;
import com.kkcloud.risk.model.RiskEntryImport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class RiskEntryListCsvHelper {

    public static String TYPE = "text/csv";
    String[] HEADERS = {"* Member account number",
                        "* Member name",
                        "Mobile phone number",
                        "Bank card number",
                        "Deposit name",
                        "* Risk level",
                        "* Entry time",
                        "* Operator",
                        "* Reason for entry",
                        "Remarks (length cannot exceed 100 characters)"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
        return true;
        }
        return false;
    }
    public static List<RiskEntryImport> csvToRiskHelper(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
             List<RiskEntryImport> riskEntryBatchListModelArrayList = new ArrayList<RiskEntryImport>();
             Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                RiskEntryImport riskEntryListModel = new RiskEntryImport(

                        csvRecord.get("* Member name"),
                        csvRecord.get("* Member account number"),
                        csvRecord.get("Deposit name"),
                        csvRecord.get("Bank card number"),
                        csvRecord.get("* Reason for entry"),
                        csvRecord.get("Mobile phone number"),
                        csvRecord.get("* Entry time"),
                        csvRecord.get("Remarks (length cannot exceed 100 characters)"),
                        Integer.parseInt(csvRecord.get("* Risk level")),
                        csvRecord.get("* Operator")
                );
                riskEntryBatchListModelArrayList.add(riskEntryListModel);
            }
            return riskEntryBatchListModelArrayList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    public static ByteArrayInputStream riskHelperToCsv(List<RiskEntryExport> riskEntryListModels) {
        final CSVFormat format = CSVFormat.RFC4180.withHeader(  "* Member account number",
                                                                "* Member name",
                                                                "Mobile phone number",
                                                                "Bank card number",
                                                                "Deposit name",
                                                                "* Risk level",
                                                                "* Entry time",
                                                                "* Operator",
                                                                "* Reason for entry",
                                                                "Remarks (length cannot exceed 100 characters)");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (RiskEntryExport riskEntryListEntity : riskEntryListModels) {
                List<? extends Serializable> data = Arrays.asList(
                        riskEntryListEntity.getMember_account(),
                        riskEntryListEntity.getMember_name(),
                        riskEntryListEntity.getContact_number(),
                        riskEntryListEntity.getBank_account_no(),
                        riskEntryListEntity.getDepositor_name(),
                        riskEntryListEntity.getRisk_id(),
                        riskEntryListEntity.getCreated_at(),
                        riskEntryListEntity.getCreated_by(),
                        riskEntryListEntity.getEntry_reason(),
                        riskEntryListEntity.getRemarks());
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to export data to CSV file: " + e.getMessage());
        }
    }
}
