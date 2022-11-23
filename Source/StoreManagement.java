// import java.io.*;
// import java.util.*;

// public class StoreManagement {
//     private ArrayList<Staff> staffs;
//     private ArrayList<String> workingTime;
//     private ArrayList<Invoice> invoices;
//     private ArrayList<InvoiceDetails> invoiceDetails;
//     private ArrayList<Drink> drinks;

//     public StoreManagement(String staffPath, String workingTimePath, String invoicesPath, String detailsPath, String drinksPath) {
//         this.staffs = loadStaffs(staffPath);
//         this.workingTime = loadFile(workingTimePath);
//         this.invoices = loadInvoices(invoicesPath);
//         this.invoiceDetails = loadInvoicesDetails(detailsPath);
//         this.drinks = loadDrinks(drinksPath);
//     }

//     public ArrayList<Staff> getStaffs() {
//         return this.staffs;
//     }
//     public void setStaffs(ArrayList<Staff> staffs){
//         this.staffs = staffs;
//     }
//     public ArrayList<String> getWorking() {
//         return this.workingTime;
//     }
    
//     public ArrayList<Drink> getDrinks() {
//         return this.drinks;
//     }
//     public ArrayList<Invoice> getInvoices() {
//         return this.invoices;
//     }
//     public ArrayList<InvoiceDetails> getInvoiceDetails() {
//         return this.invoiceDetails;
//     }


//     public ArrayList<Drink> loadDrinks(String filePath) {
//         ArrayList<Drink> drinksResult = new ArrayList<Drink>();
//         ArrayList<String> drinks = loadFile(filePath);

//         for (String drink : drinks) {
//             String[] information = drink.split(",");
//             drinksResult.add(new Drink(information[0], Integer.parseInt(information[1])));
//         }

//         return drinksResult;
//     }
//     public ArrayList<Invoice> loadInvoices(String filePath) {
//         ArrayList<Invoice> invoiceResult = new ArrayList<Invoice>();
//         ArrayList<String> invoices = loadFile(filePath);

//         for (String invoice : invoices) {
//             String[] information = invoice.split(",");
//             invoiceResult.add(new Invoice(information[0], information[1], information[2]));
//         }

//         return invoiceResult;
//     }
//     public ArrayList<InvoiceDetails> loadInvoicesDetails(String filePath) {
//         ArrayList<InvoiceDetails> invoiceResult = new ArrayList<InvoiceDetails>();
//         ArrayList<String> invoices = loadFile(filePath);

//         for (String invoice : invoices) {
//             String[] information = invoice.split(",");
//             invoiceResult.add(new InvoiceDetails(information[0], information[1], Integer.parseInt(information[2])));
//         }

//         return invoiceResult;
//     }

//     // requirement 1
//     public ArrayList<Staff> loadStaffs(String filePath) {

//         ArrayList<Staff> staffResult = new ArrayList<Staff>();
        
//         ArrayList<String> staffs = loadFile(filePath);
        
//         for (String staff : staffs) {
//             String[] information = staff.split(",");
//             if (information.length == 3) {
//                 staffResult.add(new SeasonalStaff(information[0], information[1], Integer.parseInt(information[2])));
//             } else if (information.length == 4) {
//                 staffResult.add(new FullTimeStaff(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3])));
//             } else {
//                 staffResult.add(new Manager(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3]), Integer.parseInt(information[4])));
//             }
//         }

//         return staffResult;
//     }
    
//     // requirement 2
//     public ArrayList<SeasonalStaff> getTopFiveSeasonalStaffsHighSalary() {

//         ArrayList<Staff> staffs = getStaffs();
//         ArrayList<String> working = getWorking();

//         ArrayList<SeasonalStaff> seasonalStaffs = new ArrayList<SeasonalStaff>();
//         ArrayList<SeasonalStaff> highSalary = new ArrayList<SeasonalStaff>();
//         ArrayList<Double> highestSalary = new ArrayList<Double>();

//         for (Staff s : staffs) {
//             String[] information = s.toString().split("_");
//             if (information.length == 3) {
//                 seasonalStaffs.add(new SeasonalStaff(information[0], information[1], Integer.parseInt(information[2])));
//             }
//         }

//         for (SeasonalStaff sea : seasonalStaffs) {
//             for (String w : working) {
//                 String[] tempSea = sea.toString().split("_");
//                 String[] tempW = w.split(",");
//                 if (tempSea[0].equals(tempW[0])) {
//                     highestSalary.add(Double.parseDouble(tempSea[2]) * Double.parseDouble(tempW[1]));
//                 }
//             }
//         }
        
//         double temp = 0;
//         SeasonalStaff seasonTemp = new SeasonalStaff("", "", 0);

//         for (int i = 0; i < highestSalary.size(); i++) {
//             for (int j = i + 1; j < highestSalary.size(); j++) {
//                 if (highestSalary.get(i) > highestSalary.get(j)) {
//                     temp = highestSalary.get(j);
//                     highestSalary.set(j, highestSalary.get(i));
//                     highestSalary.set(i, temp);
//                     seasonTemp = seasonalStaffs.get(j);
//                     seasonalStaffs.set(j, seasonalStaffs.get(i));
//                     seasonalStaffs.set(i, seasonTemp);
//                 }
//             }
//         }

//         for (int i = seasonalStaffs.size() - 1; i > seasonalStaffs.size() - 6; i--) {
//             highSalary.add(seasonalStaffs.get(i));
//         }

//         return highSalary;         
//     }

//     // requirement 3
//     public ArrayList<FullTimeStaff> getFullTimeStaffsHaveSalaryGreaterThan(int lowerBound) {
        
//         ArrayList<Staff> staffs = getStaffs();
//         ArrayList<String> working = getWorking();

//         ArrayList<Staff> tempStaffs = new ArrayList<Staff>();
//         ArrayList<FullTimeStaff> staffHigher = new ArrayList<FullTimeStaff>();
//         ArrayList<Double> higherSalary = new ArrayList<Double>();

//         for (int i = 0; i < staffs.size(); i++) {
//             if (staffs.get(i) instanceof FullTimeStaff){
//                 tempStaffs.add((FullTimeStaff)staffs.get(i));
//             } else if (staffs.get(i) instanceof Manager) {
//                 tempStaffs.add((Manager)staffs.get(i));
//             }
//         }

//         for (Staff s : tempStaffs) {
//             for (String w : working) {
//                 String[] tempS = s.toString().split("_");
//                 String[] tempW = w.split(",");
//                 if (tempS[0].equals(tempW[0])) {
//                     higherSalary.add(s.paySalary(Integer.parseInt(tempW[1])));
//                 }
//             }
//         }

//         for (int i = 0; i < higherSalary.size(); i++) {
//                 if (higherSalary.get(i) > lowerBound) {
//                     staffHigher.add((FullTimeStaff)tempStaffs.get(i));                
//             }
//         }

//         return staffHigher;
//     }

//     // requirement 4
//     public double totalInQuarter(int quarter) {

//         double total = 0;
//         ArrayList<Drink> drinks = getDrinks();
//         ArrayList<Invoice> invoices = getInvoices();

//         ArrayList<InvoiceDetails> invoiceDetails = getInvoiceDetails();
//         ArrayList<Invoice> invoicesTemp = new ArrayList<Invoice>();
//         ArrayList<InvoiceDetails> invoicesDetailsTemp = new ArrayList<InvoiceDetails>();


//         for (int i = 0; i < invoices.size(); i++) {
//             String[] token = invoices.get(i).getDate().split("/");
//             if (quarter == 1) {
//                 if (Integer.parseInt(token[1]) >= 1 && Integer.parseInt(token[1]) <= 3) {
//                     invoicesTemp.add(invoices.get(i));
//                 } 
//             } else if (quarter == 2) {
//                 if (Integer.parseInt(token[1]) >= 4 && Integer.parseInt(token[1]) <= 6) {
//                     invoicesTemp.add(invoices.get(i));
//                 }
//             } else if (quarter == 3) {
//                 if (Integer.parseInt(token[1]) >= 7 && Integer.parseInt(token[1]) <= 9) {
//                     invoicesTemp.add(invoices.get(i));
//                 }
//             } else {
//                 if (Integer.parseInt(token[1]) >= 10 && Integer.parseInt(token[1]) <= 12) {
//                     invoicesTemp.add(invoices.get(i));
//                 }
//             }
//         }

//         for (int i = 0; i < invoicesTemp.size(); i++) {
//             for (int j = 0; j < invoiceDetails.size(); j++) {
//                 if (invoicesTemp.get(i).getInvoiceID().equals(invoiceDetails.get(j).getInvoiceID())) {
//                     invoicesDetailsTemp.add(invoiceDetails.get(j));
//                 }
//             }
//         }

//         for (int i = 0; i < invoicesDetailsTemp.size(); i++) {
//             for (int j = 0; j < drinks.size(); j++) {
//                 if (invoicesDetailsTemp.get(i).getDName().equals(drinks.get(j).getdName())) {
//                     total += invoicesDetailsTemp.get(i).getAmount() * drinks.get(j).getPrice();
//                 }
//             }
//         }
        
//         return total;
//     }

//     // requirement 5
//     public Staff getStaffHighestBillInMonth(int month) {

//         Staff maxStaff = null;
//         double high = 0;    

//         ArrayList<Staff> staffs = getStaffs();
//         ArrayList<Drink> drinks = getDrinks();
//         ArrayList<Invoice> invoices = getInvoices();
//         ArrayList<InvoiceDetails> invoiceDetails = getInvoiceDetails();

//         ArrayList<Double> highestBill = new ArrayList<Double>();

//         for (Staff staff: staffs) {
            
//             double highest = 0;
//             ArrayList<Invoice> invoicesTemp = new ArrayList<Invoice>();
            
//             for (Invoice in : invoices) {
//                 String[] token = in.getDate().split("/");
//                 if (Integer.parseInt(token[1]) == month) {
//                     if (staff.getsID().equals(in.getStaffID())) {
//                         invoicesTemp.add(in);
//                     }
//                 }
//             }

//             ArrayList<InvoiceDetails> invoicesDetailsTemp = new ArrayList<InvoiceDetails>();

//             for (Invoice i : invoicesTemp) {
//                 for (InvoiceDetails iD : invoiceDetails) {
//                     if (i.getInvoiceID().equals(iD.getInvoiceID())) {
//                         invoicesDetailsTemp.add(iD);
//                     }
//                 }
//             }

//             for (Drink d : drinks) {
//                 for (InvoiceDetails iDetails : invoicesDetailsTemp) {
//                     if (d.getdName().equals(iDetails.getDName())) {
//                         highest += d.getPrice() * iDetails.getAmount();
//                         highestBill.add(highest);
//                     }
//                 }
//             }

//             for (int i = 0; i < highestBill.size(); i++) {
//                 if (highestBill.get(i) > high) {
//                     maxStaff = staff;
//                     high = highestBill.get(i);
//                 }
//             }
//         }
//         return maxStaff;
//     }

//     // load file as list
//     public static ArrayList<String> loadFile(String filePath) {
//         String data = "";
//         ArrayList<String> list = new ArrayList<String>();

//         try {
//             FileReader reader = new FileReader(filePath);
//             BufferedReader fReader = new BufferedReader(reader);

//             while ((data = fReader.readLine()) != null) {
//                 list.add(data);
//             }

//             fReader.close();
//             reader.close();

//         } catch (Exception e) {
//             System.out.println("Cannot load file");
//         }
//         return list;
//     }

//     public void displayStaffs() {
//         for (Staff staff : this.staffs) {
//             System.out.println(staff);
//         }
//     }

//     public <E> boolean writeFile(String path, ArrayList<E> list) {
//         try {
//             FileWriter writer = new FileWriter(path);
//             for (E tmp : list) {
//                 writer.write(tmp.toString());
//                 writer.write("\r\n");
//             }
//             writer.close();
//             System.out.println("Successfully wrote to the file.");
//         } catch (IOException e) {
//             System.out.println("Error.");
//             return false;
//         }

//         return true;
//     }

//     public <E> boolean writeFile(String path, E object) {
//         try {
//             FileWriter writer = new FileWriter(path);

//             writer.write(object.toString());
//             writer.close();

//             System.out.println("Successfully wrote to the file.");
//         } catch (IOException e) {
//             System.out.println("Error.");
//             return false;
//         }

//         return true;
//     }
// }

import java.io.*;
import java.util.*;

public class StoreManagement {
    private ArrayList<Staff> staffs;
    private ArrayList<String> workingTime;
    private ArrayList<Invoice> invoices;
    private ArrayList<InvoiceDetails> invoiceDetails;
    private ArrayList<Drink> drinks;
    private String StPath = "";
    private String WtPath = "";
    private String IvPath = "";
    private String DrPath = "";
    private String DtPath = "";


    public StoreManagement(String staffPath, String workingTimePath, String invoicesPath, String detailsPath, String drinksPath) {
        this.staffs = loadStaffs(staffPath);
        this.workingTime = loadFile(workingTimePath);
        this.invoices = loadInvoices(invoicesPath);
        this.invoiceDetails = loadInvoicesDetails(detailsPath);
        this.drinks = loadDrinks(drinksPath);
        StPath = staffPath;
        WtPath = workingTimePath;
        IvPath = invoicesPath;
        DtPath = detailsPath;
        DrPath = drinksPath;
    }

    public ArrayList<Staff> getStaffs() {
        return this.staffs;
    }
    public ArrayList<Drink> getDrinks() {
        return this.drinks;
    }
    public ArrayList<String> getWorkingTime() {
        return workingTime;
    }
    public void setStaffs(ArrayList<Staff> staffs){
        this.staffs = staffs;
    }
    
    public ArrayList<Drink> loadDrinks(String filePath) {
        ArrayList<Drink> drinksResult = new ArrayList<Drink>();
        ArrayList<String> drinks = loadFile(filePath);

        for (String drink : drinks) {
            String[] information = drink.split(",");
            drinksResult.add(new Drink(information[0], Integer.parseInt(information[1])));
        }

        return drinksResult;
    }

    public ArrayList<Invoice> loadInvoices(String filePath) {
        ArrayList<Invoice> invoiceResult = new ArrayList<Invoice>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new Invoice(information[0], information[1], information[2]));
        }

        return invoiceResult;
    }

    public ArrayList<InvoiceDetails> loadInvoicesDetails(String filePath) {
        ArrayList<InvoiceDetails> invoiceResult = new ArrayList<InvoiceDetails>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new InvoiceDetails(information[0], information[1], Integer.parseInt(information[2])));
        }

        return invoiceResult;
    }

    // requirement 1
    public ArrayList<Staff> loadStaffs(String filePath) {
        //code here and modify the return value
        ArrayList<Staff> staffsResult1 = new ArrayList<Staff>();
        ArrayList<String> staffs = loadFile(filePath);

        for (String staff : staffs) {
            String[] information = staff.split(",");
            if (information.length == 4) {
                staffsResult1.add(new FullTimeStaff(information[0], information[1], Double.parseDouble(information[2]),Double.parseDouble(information[3])));
            }
            if (information.length == 3) {
                staffsResult1.add(new SeasonalStaff(information[0], information[1], Double.parseDouble(information[2])));
            }
            if (information.length == 5) {
                staffsResult1.add(new Manager(information[0], information[1], Double.parseDouble(information[2]),Double.parseDouble(information[3]),Double.parseDouble(information[4])));
            }
        }

        return staffsResult1;
    }

    // requirement 2
    public ArrayList<SeasonalStaff> getTopFiveSeasonalStaffsHighSalary() {
        //code here and modify the return value
        ArrayList<SeasonalStaff> staffsResult = new ArrayList<SeasonalStaff>();
        ArrayList<SeasonalStaff> top5 = new ArrayList<SeasonalStaff>();
        ArrayList<String> staffs = loadFile(StPath);
        Collections.sort(staffs);
        for (String staff : staffs) {
            String[] information = staff.split(",");
            if (information.length == 3) {
                staffsResult.add(new SeasonalStaff(information[0], information[1], Double.parseDouble(information[2])));
            }
        }
        ArrayList<String> staffs1 = loadFile(StPath);
        Collections.sort(staffs1);
        int u= 0;
        double[] hourlyWage = new double[staffsResult.size()];
        for (String staff : staffs1) {
            String[] information = staff.split(",");
            if (information.length == 3) {
                hourlyWage[u++] = Double.parseDouble(information[2]);
            }
        }
        u = 0;
        int[] workingTime = new int[staffsResult.size()];
        ArrayList<String> woking = loadFile(WtPath);
        Collections.sort(woking);
        for (String str : woking) {
            String[] information = str.split(",");
            if (str.indexOf("TV") != -1) {
                workingTime[u++] = Integer.parseInt(information[1]);
            }
        }
        double[] salary = new double[workingTime.length];
        for (int i = 0;i<workingTime.length;i++) {
            salary[i] = workingTime[i] * hourlyWage[i];
        }
        int[] indexTop5 = new int[5];
        double max = 0;
        int index = 0; u = 0;
        for (int o = 0;o<5;o++) {
            max = 0;
            for (int i = 0;i<workingTime.length;i++) {
                if (salary[i] > max) {
                    max = salary[i];
                }
            }
            for (int i = 0;i<workingTime.length;i++) {
                if (salary[i] == max) {
                    index = i;
                    salary[i] = 0;
                    break;
                }
            }
            indexTop5[u++] = index;
        }
        for (int i = 0;i<5;i++) {
            top5.add(staffsResult.get(indexTop5[i]));
        }
        return top5;
    }
    // requirement 3
    public ArrayList<FullTimeStaff> getFullTimeStaffsHaveSalaryGreaterThan(int lowerBound) {
        //code here and modify the return value
        ArrayList<FullTimeStaff> result = new ArrayList<FullTimeStaff>();
        ArrayList<FullTimeStaff> list = new ArrayList<FullTimeStaff>();
        ArrayList<String> staffs = loadFile(StPath);
        double[] allowance = new double[staffs.size()];
        int u = 0;
        for (String staff : staffs) {
            String[] information = staff.split(",");
            if (information.length ==4 ) {
                list.add(new FullTimeStaff(information[0], information[1], Double.parseDouble(information[2]),Double.parseDouble(information[3])));
            }
            if (information.length == 5 ) {
                list.add(new Manager(information[0], information[1], Double.parseDouble(information[2]),Double.parseDouble(information[3]),Double.parseDouble(information[4])));
                allowance[u++] = Double.parseDouble(information[4]);
            }
        }
        String[] id = new String[list.size()];
        double[] baseSalary = new double[list.size()];
        double[] bonusRate = new double[list.size()];
        u = 0;
        for (FullTimeStaff ft : list) {
            id[u] = ft.getsID();
            bonusRate[u] = ft.getBonusRate();
            baseSalary[u++] = ft.getBaseSalary();
        }
        int[] workingTime = new int[list.size()];
        ArrayList<String> woking = loadFile(WtPath);
        for (int i = 0 ; i < list.size();i++) {
            for (String str : woking) {
                String[] information = str.split(",");
                if (str.indexOf(id[i]) != -1) {
                    workingTime[i] = Integer.parseInt(information[1]);
                    break;
                }
            }
        }
        double[] salary = new double[list.size()];
        u = 0;
        for (FullTimeStaff gg : list) {
            salary[u] = gg.paySalary(workingTime[u++]);
        }
        int[] index = new int[list.size()];
        int count = 0;
        u = 0;
        for (int i = 0;i<list.size();i++) {
            if (salary[i] > lowerBound) {
                index[u++] = i;
                count ++;
            }
        }
        for (int i = 0;i< count;i++) {
            result.add(list.get(index[i]));
        }
        return result;
    }
    // requirement 4
    public double totalInQuarter(int quarter) {
        double total = 0;
        ArrayList<String> invoice = loadFile(IvPath);
        ArrayList<String> invoiceDetail = loadFile(DtPath);
        ArrayList<String> drinks = loadFile(DrPath);
        Collections.sort(invoiceDetail);

        String[]  hdInvoice = new String[invoice.size()];
        int[] mounth = new int[invoice.size()];
        String[] drinkNameHd = new String[invoiceDetail.size()];
        String[] hdInvoiceDetail = new String[invoiceDetail.size()];
        int[] soLuong = new int[invoiceDetail.size()];
        String[] drinkName = new String[drinks.size()];
        int[] price = new int[drinks.size()];
        int u = 0;

        for (String str : invoice) {
            String[] information = str.split(",");
            hdInvoice[u] = information[0];
            String[] mth = information[2].split("/");
            mounth[u++] = Integer.parseInt(mth[1]); 
        }
        u=0;
        for (String str : invoiceDetail) {
            String[] information = str.split(",");
            hdInvoiceDetail[u] = information[0];
            drinkNameHd[u] = information[1];
            soLuong[u++] = Integer.parseInt(information[2]);
        }
        u=0;
        for (String str : drinks) {
            String[] information = str.split(",");
            drinkName[u] = information[0];
            price[u++] = Integer.parseInt(information[1]);
        }
        switch (quarter) {
            case 1 : {
                int[] soLuongQuarter = new int[invoiceDetail.size()];
                String[] hdQuarter = new String[invoice.size()];
                String[] drinkNameQuarter = new String[invoiceDetail.size()];
                int count=0;
                int count1 = 0;
                int u1 = 0;
                for (int i = 0;i<invoice.size();i++) {
                    if (mounth[i] == 1 || mounth[i] == 2 || mounth[i] == 3) {
                        hdQuarter[u1++] = hdInvoice[i];
                        count ++;
                    }
                }
                u1 = 0;
                for (int i = 0;i<count;i++) {
                        for (int j = 0;j<invoiceDetail.size();j++) {
                            if (hdQuarter[i].equals(hdInvoiceDetail[j])) {
                            drinkNameQuarter[u1] = drinkNameHd[j];
                            soLuongQuarter[u1++] = soLuong[j];
                            count1++;
                        }
                    }
                }
                String s1;
                String s2;
                for (int i = 0;i<count1;i++) {
                    for (int j = 0 ;j<drinks.size();j++) {
                        s1 = drinkName[j];
                        s2 = drinkNameQuarter[i];
                        if (s1.compareTo(s2)==0) {
                            total = total + soLuongQuarter[i] * price[j];
                            break;
                        }
                    }
                }
                break;
            }
            case 2 : {
                int[] soLuongQuarter = new int[invoiceDetail.size()];
                String[] hdQuarter = new String[invoice.size()];
                String[] drinkNameQuarter = new String[invoiceDetail.size()];
                int count=0;
                int count1 = 0;
                int u1 = 0;
                for (int i = 0;i<invoice.size();i++) {
                    if (mounth[i] == 4 || mounth[i] == 5 || mounth[i] == 6) {
                        hdQuarter[u1++] = hdInvoice[i];
                        count ++;
                    }
                }
                u1 = 0;
                for (int i = 0;i<count;i++) {
                        for (int j = 0;j<invoiceDetail.size();j++) {
                            if (hdQuarter[i].equals(hdInvoiceDetail[j])) {
                            drinkNameQuarter[u1] = drinkNameHd[j];
                            soLuongQuarter[u1++] = soLuong[j];
                            count1++;
                        }
                    }
                }
                String s1;
                String s2;
                for (int i = 0;i<count1;i++) {
                    for (int j = 0 ;j<drinks.size();j++) {
                        s1 = drinkName[j];
                        s2 = drinkNameQuarter[i];
                        if (s1.compareTo(s2)==0) {
                            total = total + soLuongQuarter[i] * price[j];
                            break;
                        }
                    }
                }
                break;
            }
            case 3 : {
                int[] soLuongQuarter = new int[invoiceDetail.size()];
                String[] hdQuarter = new String[invoice.size()];
                String[] drinkNameQuarter = new String[invoiceDetail.size()];
                int count=0;
                int count1 = 0;
                int u1 = 0;
                for (int i = 0;i<invoice.size();i++) {
                    if (mounth[i] == 7 || mounth[i] == 8 || mounth[i] == 9) {
                        hdQuarter[u1++] = hdInvoice[i];
                        count ++;
                    }
                }
                u1 = 0;
                for (int i = 0;i<count;i++) {
                        for (int j = 0;j<invoiceDetail.size();j++) {
                            if (hdQuarter[i].equals(hdInvoiceDetail[j])) {
                            drinkNameQuarter[u1] = drinkNameHd[j];
                            soLuongQuarter[u1++] = soLuong[j];
                            count1++;
                        }
                    }
                }
                String s1;
                String s2;
                for (int i = 0;i<count1;i++) {
                    for (int j = 0 ;j<drinks.size();j++) {
                        s1 = drinkName[j];
                        s2 = drinkNameQuarter[i];
                        if (s1.compareTo(s2)==0) {
                            total = total + soLuongQuarter[i] * price[j];
                            break;
                        }
                    }
                }
                break;
            }
            case 4 : {
                int[] soLuongQuarter = new int[invoiceDetail.size()];
                String[] hdQuarter = new String[invoice.size()];
                String[] drinkNameQuarter = new String[invoiceDetail.size()];
                int count=0;
                int count1 = 0;
                int u1 = 0;
                for (int i = 0;i<invoice.size();i++) {
                    if (mounth[i] == 10 || mounth[i] == 11 || mounth[i] == 12) {
                        hdQuarter[u1++] = hdInvoice[i];
                        count ++;
                    }
                }
                u1 = 0;
                for (int i = 0;i<count;i++) {
                        for (int j = 0;j<invoiceDetail.size();j++) {
                            if (hdQuarter[i].equals(hdInvoiceDetail[j])) {
                            drinkNameQuarter[u1] = drinkNameHd[j];
                            soLuongQuarter[u1++] = soLuong[j];
                            count1++;
                        }
                    }
                }
                String s1;
                String s2;
                for (int i = 0;i<count1;i++) {
                    for (int j = 0 ;j<drinks.size();j++) {
                        s1 = drinkName[j];
                        s2 = drinkNameQuarter[i];
                        if (s1.compareTo(s2)==0) {
                            total = total + soLuongQuarter[i] * price[j];
                            break;
                        }
                    }
                }
                break;
            }
        }
        return total;
    }
    // requirement 5
    public Staff getStaffHighestBillInMonth(int month) {
        Staff maxStaff = null;
        double moneymax=0;
        
        for(Staff staff : this.staffs) {

            double money = 0;
            ArrayList<Invoice> result = new ArrayList<Invoice>();

            for(Invoice invoice : this.invoices){
                String[] monthInvoice=invoice.getDate().split("/");
                if(staff.getsID().equals(invoice.getStaffID())&&Integer.parseInt(monthInvoice[1])==month){
                    result.add(invoice);
                }
            }

            for(Invoice invoice : result){
                for(InvoiceDetails invoiceDetail: this.invoiceDetails){
                    if(invoice.getInvoiceID().equals(invoiceDetail.getInvoiceID())){
                        for(Drink drink : drinks){
                            if(drink.getdName().equals(invoiceDetail.getDName())){
                                money += drink.getPrice()*invoiceDetail.getAmount();
                            }
                        }
                    }
                }
            }
            
            if(moneymax < money){
                moneymax = money;
                maxStaff = staff;
            }
        }
        return maxStaff;
    }

    // load file as list
    public static ArrayList<String> loadFile(String filePath) {
        String data = "";
        ArrayList<String> list = new ArrayList<String>();

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader fReader = new BufferedReader(reader);

            while ((data = fReader.readLine()) != null) {
                list.add(data);
            }

            fReader.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("Cannot load file");
        }
        return list;
    }

    public void displayStaffs() {
        for (Staff staff : this.staffs) {
            System.out.println(staff);
        }
    }

    public <E> boolean writeFile(String path, ArrayList<E> list) {
        try {
            FileWriter writer = new FileWriter(path);
            for (E tmp : list) {
                writer.write(tmp.toString());
                writer.write("\r\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }

    public <E> boolean writeFile(String path, E object) {
        try {
            FileWriter writer = new FileWriter(path);

            writer.write(object.toString());
            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
        
    }
}