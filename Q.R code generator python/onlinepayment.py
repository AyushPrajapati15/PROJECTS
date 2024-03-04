import qrcode
upi_id = input("Enter your upi id")

# upi://pay?pa=UPI_ID&pn=NAME&am=AMOUNT&cu=CURRENCY&tm=MESSAGE


phonepe_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'
googlepay_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'


#Create qr code for each payment app
phonepe_qr=qrcode.make(phonepe_url)
googlepay_qr=qrcode.make(googlepay_url)



#Save the qr code to image file
phonepe_qr.save('phonepe_qr.png')
googlepay_qr.save('google_pay_qr.png')