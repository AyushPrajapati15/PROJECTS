import qrcode
upi_id = input("Enter your upi id")

# upi://pay?pa=UPI_ID&pn=NAME&am=AMOUNT&cu=CURRENCY&tm=MESSAGE


phonepe_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'
googlepay_url=f'upi://pay?pa={upi_id}&pn=Recipient%20Name&mc=1234'


#Create qr code for each payment app
phonepe_qr=qrcode.make(phonepe_url)
