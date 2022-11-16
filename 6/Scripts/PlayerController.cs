using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float moveSpeed;
    //public Rigidbody body;
    public float jumpForce;

    public CharacterController controller;

    private Vector3 moveDirection;

    public float gravityScale;
    // Start is called before the first frame update
    void Start()
    {
        //body = GetComponent<Rigidbody>();
        controller = GetComponent<CharacterController>();
    }

    // Update is called once per frame
    void Update()
    {
        //crouching = Input.GetKey(KeyCode.C);

        //moveDirection = new Vector3(Input.GetAxis("Horizontal") * moveSpeed, moveDirection.y, Input.GetAxis("Vertical") * moveSpeed);

        float yStore = moveDirection.y;
        moveDirection = (transform.forward * Input.GetAxis("Vertical") * moveSpeed) + (transform.right * Input.GetAxis("Horizontal") * moveSpeed);
        moveDirection = moveDirection.normalized * moveSpeed;
        moveDirection.y = yStore;

        if(controller.isGrounded){
            moveDirection.y = -3f;
            if(Input.GetButtonDown("Jump")){
                moveDirection.y = jumpForce;
            }
        }

        moveDirection.y += (Physics.gravity.y * Time.deltaTime * gravityScale);
        controller.Move(moveDirection * Time.deltaTime);
    }
}
