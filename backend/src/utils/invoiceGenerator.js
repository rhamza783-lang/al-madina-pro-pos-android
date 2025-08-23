const generateInvoiceNumber = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  // Generate a random 5-digit number
  const random = Math.floor(10000 + Math.random() * 90000);
  
  return `INV-${year}-${month}-${random}`;
};

module.exports = { generateInvoiceNumber };
