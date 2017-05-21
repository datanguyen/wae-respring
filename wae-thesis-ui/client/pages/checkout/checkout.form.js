import React from 'react'
import cssModules from 'react-css-modules'
import { MuiThemeProvider, TextField, RaisedButton, List, ListItem, Subheader, Divider, Avatar, SelectField,
  MenuItem, RadioButtonGroup, RadioButton, Dialog } from 'material-ui'
import { Col, Row } from 'react-bootstrap'
import isEmpty from 'lodash/isEmpty'
import style from './checkout.style.scss'
import cities from './data/city.data'
import districts from './data/district.data'
import wards from './data/ward.data'
import banks from './data/bank.data'


@cssModules(style, {errorWhenNotFound: false})
export class CheckoutForm extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      username: '',
      email: '',
      city: '',
      district: '',
      ward: '',
      number: '',
      phone: '',
      payment: 'COD',
      atmBank: '',
      atmNumber: '',
      errors: {},
      isLoading: false,
      totalPrice: this.calculateTotalPrice(),
      isATM: false,
      isFinish: false
    }
  }

  validateInput() {
    let { username, email, city, district, ward, number, phone, atmBank, atmNumber, isATM} = this.state
    let errors = {}

    if (username === '') {
      errors.username = "This is a required field"
    }

    if (email === '') {
      errors.email = "This is a required field"
    }

    if (city === '') {
      errors.city = "This is a required field"
    }

    if (district === '') {
      errors.district = "This is a required field"
    }

    if (ward === '') {
      errors.ward = "This is a required field"
    }

    if (number === '') {
      errors.number = "This is a required field"
    }

    if (phone === '') {
      errors.phone = "This is a required field"
    }

    if (phone.length > 11 || phone.length < 10) {
      errors.phone = "Phone number should be in length 10 - 11"
    }

    if (isATM) {
      if (atmBank === '') {
        errors.atmBank = 'This is a required field'
      }

      if (atmNumber.length < 10 || atmNumber.length > 15) {
        errors.atmNumber = 'ATM number should be in length 10 -15'
      }
    }

    if (!isEmpty(errors)) {
      this.setState({ errors })
    }

    return {
      errors,
      isValid: !isEmpty(errors)
    }
  }

  onChange(e) {
    e.preventDefault()
    this.setState({ [e.target.name]: e.target.value })
  }

  onChangeSelectField(name, value) {
    this.setState({ [name]: value})
  }

  onSubmit(e) {
    e.preventDefault()
    let { username, email, city, district, ward, number, phone, isATM,
      payment, atmBank, atmNumber, totalPrice} = this.state
    let { isValid } = this.validateInput()

    if (!isValid) {
      let checkout = { username, email, city, district, ward, number, phone, payment, totalPrice}

      if (isATM) {
        checkout.atmNumber = atmNumber
        checkout.atmBank = atmBank
      }
      this.setState({ errors: {}})
      this.props.userCheckoutRequest(checkout)
      this.setState({ isFinish: true})
      setTimeout(() => window.location.replace("/"), 5000)
    }
  }

  handlerChangeQuantity(e) {
    let newPrice = e.target.name * e.target.value
    this.setState({ totalPrice: this.state.totalPrice + newPrice})
  }

  handleChangePaymentMethod(value) {
    this.setState({ payment: value})
    this.setState(value === 'ATM' ? { isATM: true } : { isATM: false })
  }

  calculateTotalPrice() {
    let totalPrice = 0

    this.props.cart.forEach(product => totalPrice = totalPrice + product.productPrice)

    return totalPrice
  }

  static citiesMenu() {
    return cities.map(cityName => (
        <MenuItem
          key={cityName}
          value={cityName}
          primaryText={cityName}
        />
    ))
  }

  static districtsMenu() {
    return districts.map(districtName => (
      <MenuItem
        key={districtName}
        value={districtName}
        primaryText={districtName}
      />
    ))
  }

  static wardsMenu() {
    return wards.map(wardName => (
      <MenuItem
        key={wardName}
        value={wardName}
        primaryText={wardName}
      />
    ))
  }

  static banksMenu() {
    return banks.map(bankName => (
      <MenuItem
        key={bankName}
        value={bankName}
        primaryText={bankName}
      />
    ))
  }

  render() {
    let { username, email, city, district, ward, number, phone, errors,
      isATM, atmBank, atmNumber, isLoading } = this.state
    let fieldStyle = { float: "right", fontSize: "15px", marginTop: "15px", fontWeight: "bolder"}

    return (
    <Row>
      <Col md={5}>
          <section styleName="signin-section" style={{marginLeft: "5%", padding: "10px 30px"}}>
            <MuiThemeProvider>
              <Subheader style={{color: "blue", fontSize: "20px"}}>Your Shipping's Address</Subheader>
            </MuiThemeProvider>
            <MuiThemeProvider>
              <Divider/>
            </MuiThemeProvider>
            <section style={{marginTop: "20px"}}>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>Name: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <TextField name="username"
                               value={username}
                               errorText={errors.username}
                               hintText="Fullname"
                               onChange={(e) => this.onChange(e)}
                    />
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>Email: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <TextField name="email"
                               value={email}
                               errorText={errors.email}
                               hintText="Email"
                               fullWidth={false}
                               onChange={(e) => this.onChange(e)}
                    />
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>City: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <SelectField
                      value={city}
                      errorText={errors.city}
                      hintText="Choose City"
                      onChange={(e, index, value) => this.onChangeSelectField("city", value)}
                    >
                      { CheckoutForm.citiesMenu() }
                    </SelectField>
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>District: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <SelectField
                      value={district}
                      errorText={errors.district}
                      hintText="Choose District"
                      onChange={(e, index, value) => this.onChangeSelectField("district", value)}
                    >
                      { CheckoutForm.districtsMenu() }
                    </SelectField>
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>Ward: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <SelectField
                      value={ward}
                      errorText={errors.ward}
                      hintText="Choose Ward"
                      onChange={(e, index, value) => this.onChangeSelectField("ward", value)}
                    >
                      { CheckoutForm.wardsMenu() }
                    </SelectField>
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>Detail: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <TextField name="number"
                               value={number}
                               errorText={errors.number}
                               hintText="Detail Address"
                               multiLine={true}
                               rowsMax={4}
                               onChange={(e) => this.onChange(e)}
                    />
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <p style={fieldStyle}>Phone: </p>
                </Col>
                <Col md={3}>
                  <MuiThemeProvider>
                    <TextField name="phone"
                               value={phone}
                               errorText={errors.phone}
                               hintText="Phone Number"
                               onChange={(e) => this.onChange(e)}
                    />
                  </MuiThemeProvider>
                </Col>
              </Row>
              <Row>
                <Col md={2}>
                  <MuiThemeProvider>
                    <RaisedButton label="Checkout"
                                  labelPosition="before"
                                  backgroundColor="#ff6a00"
                                  labelColor="white"
                                  style={{margin: "40px 0 10px 0"}}
                                  onTouchTap={(e) => this.onSubmit(e)}
                                  disabled={isLoading}
                    />
                  </MuiThemeProvider>
                </Col>
              </Row>
            </section>
          </section>
        </Col>
      <Col md={4}>
          <section styleName="signin-section">
            <MuiThemeProvider>
              <List>
                <Subheader style={{color: "blue", fontSize: "20px"}}>Your Shopping's Cart</Subheader>
                <Divider />
                <Row>
                  <Col md={8}>
                    <Subheader style={{color: "black", fontSize: "20px"}}>Product</Subheader>
                  </Col>
                  <Col md={1}>
                    <Subheader style={{color: "black", fontSize: "20px"}}>Quantity</Subheader>
                  </Col>
                </Row>
                {
                  this.props.cart.map(product => {
                    let { productName, productPrice, productUrl } = product

                    return (
                      <Row>
                        <Col md={8}>
                          <ListItem
                            leftAvatar={<Avatar src={productUrl}/>}
                            primaryText={productName}
                            secondaryText={<p>${productPrice}</p>}
                          />
                        </Col>
                        <Col md={1}>
                          <select
                            name={productPrice}
                            onChange={(e) => this.handlerChangeQuantity(e)}
                            style={{marginTop: "20px", marginLeft: "20px"}}
                          >
                            <option value="1" selected={true}>1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="4">5</option>
                          </select>
                        </Col>
                      </Row>
                    )
                  })
                }
                <Divider />
                <Subheader style={{color: "black", fontSize: "20px"}}>
                  Total Price: <span style={{color: "#ff6a00", fontWeight: "bolder"}}>${this.state.totalPrice}</span>
                </Subheader>
              </List>
            </MuiThemeProvider>
          </section>
        </Col>
      <Col md={3}>
          <section styleName="signin-section" style={{marginRight: "5%"}}>
            <MuiThemeProvider>
              <List>
                <Subheader style={{color: "blue", fontSize: "20px"}}>Payment Method</Subheader>
                <Divider />
                <Row>
                  <Col md={10}>
                    <RadioButtonGroup
                      name="payMethod"
                      defaultSelected="COD"
                      onChange={(e, value) => this.handleChangePaymentMethod(value)}
                    >
                      <RadioButton
                        value="COD"
                        label="Cost on Delivery"
                        style={{marginTop: "20px"}}
                      />
                      <RadioButton
                        value="ATM"
                        label="ATM/Internet Banking"
                        style={{marginTop: "20px"}}
                      />
                    </RadioButtonGroup>
                  </Col>
                </Row>
                <section style={{ display: isATM ? "initial" : "none", marginTop: "300px"}} >
                  <Divider/>
                  <Row>
                    <Col md={8}>
                      <MuiThemeProvider>
                        <SelectField
                          value={atmBank}
                          errorText={errors.atmBank}
                          hintText="Choose Bank"
                          hintStyle={{color: "blue"}}
                          onChange={(e, i, value) => this.onChangeSelectField("atmBank", value)}
                        >
                          { CheckoutForm.banksMenu() }
                        </SelectField>
                      </MuiThemeProvider>
                      <MuiThemeProvider>
                        <TextField name="atmNumber"
                                   value={atmNumber}
                                   errorText={errors.atmNumber}
                                   hintText="Card's Number"
                                   hintStyle={{color: "blue"}}
                                   onChange={(e) => this.onChange(e)}
                        />
                      </MuiThemeProvider>
                    </Col>
                  </Row>
                </section>
              </List>
            </MuiThemeProvider>
          </section>
        </Col>
      <MuiThemeProvider>
        <Dialog
          open={this.state.isFinish}
          title="Congratulations"
          actions={
            <RaisedButton
              label="BACK"
              keyboardFocused={true}
              onTouchTap={() => window.location.replace("/")}
              secondary={true}
            />
          }
        >
          You have been finished to checkout. Click the button to continue shopping or will
          be automatically go back to homepage in 5s.
        </Dialog>
      </MuiThemeProvider>
    </Row>
    )
  }
}

CheckoutForm.propTypes = {
  cart: React.PropTypes.array.isRequired,
  userCheckoutRequest: React.PropTypes.func.isRequired
}
