import qrcode
upi_id = input("Enter your upi id")

# upi://pay?pa=UPI_ID&pn=NAME&am=AMOUNT&cu=CURRENCY&tm=MESSAGE


phonepe_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'
google_pay_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'


#Create qr code for each payment app
phonepe_qr=qrcode.make(phonepe_url)
google_pay_qr=qrcode.make(google_pay_url)



#Save the qr code to image file
phonepe_qr.save('phonepe_qr.png')
google_pay_qr.save('google_pay_qr.png')