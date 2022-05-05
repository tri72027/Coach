export interface Ticket {

  'ticketID': number;
  'ticketCode': string;
  'amountSeats': number;
  'date': string;
  'seat': string;
  'price': number;
  'tripID': number;
  'userID': number;
  'statusID': number;
}
export interface FullTicket {

  'ticketID': number |string;
  'dateBuyTicket': string;
  'dateGo': string;
  'ticketCode': string;
  'amountSeats': number;
  'seat': string;
  'provinceStartName': string;
  'provinceEndName': string;
  'licensePlates': string;
  'price': number;
  'userID': number;
  'status': string
}
